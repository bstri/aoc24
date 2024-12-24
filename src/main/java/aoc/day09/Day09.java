package aoc.day09;

import aoc.Board;
import aoc.Day;
import aoc.Utils;

import java.util.*;

public class Day09 implements Day {

    @Override
    public String part1(String input) {
        int[] digits = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            digits[i] = input.charAt(i) - '0';
        }

        long checksum = 0;

        // pointers to head and tail indexes in digits
        int i = 0;
        int j = digits.length - 1 - (digits.length % 2 == 0 ? 1 : 0);

        int position = 0; // stores position of next file block placement
        int id = 0; // stores head pointer file id
        int endId = Math.ceilDiv(digits.length, 2) - 1; // stores tail pointer file id
        int endBlocksRemaining = digits[j]; // how many blocks of tail file have yet to be compacted
        int freeSpace = 0;

        while (i <= j) {
            if (freeSpace == 0) {
                int fileSize = digits[i];
                if (id == endId) {
                    fileSize = endBlocksRemaining;
                }
                for (int k = 0; k < fileSize; k++) {
                    checksum += (long) id *position;
//                    System.out.printf("(head) Checksum += %d*%d\n",position,id);
                    position++;
                }
                i++;
                freeSpace = digits[i];
                i++;
                id++;
            } else {
                while (freeSpace > 0 && endBlocksRemaining > 0) {
                    checksum += (long) position *endId;
//                    System.out.printf("(tail) Checksum += %d*%d\n",position,endId);
//                    System.out.println(endBlocksRemaining);
                    position++;
                    freeSpace--;
                    endBlocksRemaining--;
                }
//                if (freeSpace == 0) {
//                    i++;
//                }
                if (endBlocksRemaining == 0) {
                    j -= 2;
                    endId--;
                    endBlocksRemaining = digits[j];
                }
            }
        }

        return Long.toString(checksum);
    }

    private int digitize(char c) {
        return c - '0';
    }

    @Override
    public String part2(String input) {
        int[] idFileSizes = new int[input.length()/2 + 1];
        Map<Integer, LinkedList<Integer>> freeSpaceIndices = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            freeSpaceIndices.put(i, new LinkedList<>());
        }
        int accumulatedSpace = 0;
        for (int i = 0; i < idFileSizes.length - 1; i++) {
            int index = 2*i;
            int fileSize = digitize(input.charAt(index));
            idFileSizes[i] = fileSize;
            accumulatedSpace += fileSize;
            int freeSpace = digitize(input.charAt(index + 1));
            if (freeSpace > 0) {
                freeSpaceIndices.get(freeSpace).addLast(accumulatedSpace);
                accumulatedSpace += freeSpace;
            }
        }
        idFileSizes[idFileSizes.length - 1] = digitize(input.charAt(input.length() - 1));

        long checksum = 0;
        // have freespace hashmap of size -> [list of indices in order] LINKED LIST, min heap PriorityQueue??
        // to update, have to remove index from list, easy if linked list
        // if freespace remaining in area being moved to, have to put an index in the list of a different key.. that's annoying because it could go anywhere, linked list makes sense
        // iterate down from ids, check keys filesize to 9 inclusive in map, find min index of those

        for (int id = idFileSizes.length - 1; id > 0; id++) {
            int fileSize = idFileSizes[id];
//            int index =

        }
        return  "";




//        int[] freeSpaces = new int[input.length()/2];
//        int[] idFileSizes = new int[Math.ceilDiv(input.length(), 2)]; // in case input length is even. which it isn't
//        int[] cumulativeSpace = new int[input.length()];
//        int lastAccumulation = 0;
//        for (int i = 0; i < freeSpaces.length; i++) {
//            idFileSizes[i] = input.charAt(2*i) - '0';
//            freeSpaces[i] = input.charAt(2*i + 1) - '0';
//            cumulativeSpace[2*i] = lastAccumulation + idFileSizes[i];
//            lastAccumulation = cumulativeSpace[2*i] + freeSpaces[i];
//            cumulativeSpace[2*i+1] = lastAccumulation;
//        }
//        idFileSizes[idFileSizes.length - 1] = input.charAt(input.length() - 1) - '0';
//        cumulativeSpace[cumulativeSpace.length - 1] = lastAccumulation + idFileSizes[idFileSizes.length - 1];
//
//        long checksum = 0;
//        // have freespace hashmap of size -> [list of indices in order] LINKED LIST
//        // to update, have to remove index from list, easy if linked list
//        // if freespace remaining in area being moved to, have to put an index in the list of a different key.. that's annoying because it could go anywhere, linked list makes sense
//        // iterate down from ids, check keys filesize to 9 inclusive in map
//        int nextNonZeroFreeSpace = 0;
//        for (int id = idFileSizes.length - 1; id > 0; id--) { // TODO revisit condition
//            int fileBlockSize = idFileSizes[id];
//            for (int i = nextNonZeroFreeSpace; i < id; i++) {
//                if (freeSpaces[i] >= fileBlockSize) {
//                    // move the file
//                    freeSpaces[i] -= fileBlockSize;
//                    if (i == nextNonZeroFreeSpace && freeSpaces[i] == 0) {
//                        nextNonZeroFreeSpace++; // optimization
//                    }
//                    checksum
//                } else {
//                    // don't move the file
//                    // still update checksum
//                }
//            }
//            if (idFileSizes[id] <= freeSpaces[nextNonZeroFreeSpace]) {
//                // can move
//                freeSpaces[nextNonZeroFreeSpace] -= idFileSizes[id];
//                if (freeSpaces[nextNonZeroFreeSpace] == 0) {
//                    nextNonZeroFreeSpace++;
//                }
//            } else {
//                // can't move this block
//            }
//        }
//        // how to terminate? nextnonzerofreespace is past the file id
//
//        return Long.toString(checksum);
    }
}
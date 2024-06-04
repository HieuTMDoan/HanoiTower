package model;

import java.util.Stack;

public class Tower {
    private final Stack<Disk> myDiskStack = new Stack<>();

    public Tower(final int theDiskCount) {
        if (theDiskCount < 0) {
            throw new IllegalArgumentException("Negative disk count!");
        } else {
            initializeStack(theDiskCount);
        }
    }

    /*******************************************************************************************************************
     *                                                 GETTER METHODS                                                  *
     *******************************************************************************************************************/
    public int getDiskCount() {
        return myDiskStack.size();
    }

    public Stack<Disk> getDiskStack() {
        return myDiskStack;
    }

    /*******************************************************************************************************************
     *                                                 SETTER METHODS                                                  *
     *******************************************************************************************************************/
    public Disk popDisk() {
        Disk popDisk = null;

        if (!myDiskStack.empty()) {
            popDisk = myDiskStack.pop();
        }

        return popDisk;
    }

    public void pushDisk(final Disk theDisk) {
        if (this.canPush(theDisk)) {
            myDiskStack.push(theDisk);
        }
    }

    /*******************************************************************************************************************
     *                                                 HELPER METHODS                                                  *
     *******************************************************************************************************************/
    public boolean canPush(final Disk theDisk) {
        return (myDiskStack.size() == 0) || (myDiskStack.peek().getSize() > theDisk.getSize());
    }

    private void initializeStack(final int theDiskCount) {
        for (int i = theDiskCount; i > 0; i--) {
            myDiskStack.push(new Disk(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringStack = new StringBuilder("[");

        for (Disk disk : myDiskStack) {
            stringStack.append(disk).append(" ");
        }
        if (myDiskStack.size() > 0) {
            stringStack.deleteCharAt(stringStack.length() - 1);
        }

        stringStack.append("]");
        return stringStack.toString();
    }

    /*******************************************************************************************************************
     *                                          PACKAGE-PRIVATE CLASS                                                  *
     *******************************************************************************************************************/
    static class Disk {
        private final int size;

        private Disk(final int theSize) {
            if (theSize < 0) {
                throw new IllegalArgumentException("Negative disk size!");
            } else {
                size = theSize;
            }
        }

        private int getSize() {
            return size;
        }

        @Override
        public String toString() {
            return "#" + this.getSize();
        }
    }
}
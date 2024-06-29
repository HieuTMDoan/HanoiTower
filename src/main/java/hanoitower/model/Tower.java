package hanoitower.model;

import java.util.Stack;

public class Tower {
    private final Stack<Disk> myDiskStack = new Stack<>();

    private final int myTowerID;

    public Tower(final int theDiskCount, final int theTowerID) {
        if (theDiskCount < 0) {
            throw new IllegalArgumentException("Negative disk count!");
        } else {
            for (int i = theDiskCount; i > 0; i--) {
                myDiskStack.push(new Disk(i));
            }
            myTowerID = theTowerID;
        }
    }

    /*******************************************************************************************************************
     *                                                 GETTER METHODS                                                  *
     *******************************************************************************************************************/
    public int getDiskCount() {
        return myDiskStack.size();
    }

    public int getTowerID() {
        return myTowerID;
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

    @Override
    public boolean equals(Object theObject) {
        return theObject != null && this.getClass() == theObject.getClass() && this.myTowerID == ((Tower) theObject).myTowerID;
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
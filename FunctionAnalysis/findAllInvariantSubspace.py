from plotOfF0F1 import F0, F1, bfformat


def findInvariantSubspacesF0():
    for i in range(256):
        if (i == F0(i)) or (i == (255 - F0(i))):
            print(bfformat(i, 8))


def findInvariantSubspacesF1():
    for i in range(256):
        if (i == F1(i)) or (i == (255 - F1(i))):
            print(bfformat(i, 8))


if __name__ == '__main__':
    print("Invariant Subspaces of F0:")
    findInvariantSubspacesF0()
    print("Invariant Subspaces of F1:")
    findInvariantSubspacesF1()
    print(bfformat(51, 8))
    print(bfformat(F0(51), 8))

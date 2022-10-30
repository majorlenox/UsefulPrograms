import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

def F0(x):
    return ((lr(x, 1)) ^ (lr(x, 2)) ^ (lr(x, 7))) % 256


def F1(x):
    return ((lr(x, 3)) ^ (lr(x, 4)) ^ (lr(x, 6))) % 256


def lr(x, n):
    return (x << n) | (x >> (8 - n))


# binary full format
def bfformat(i, n):
    str1 = format(i, 'b')
    return str('0' * (n - len(str1)) + str1)


def hamweight(i):
    r = 0
    while (i > 0):
        r += int(i % 2)
        i /= 2
    return r


def F0toDataFrame():
    data = {'x(2)': [], 'x hw': [], 'F0': [], 'F0 hw': []}
    for i in range(256):
        data['x(2)'].append(bfformat(i, 8))
        data['x hw'].append(hamweight(i))
        data['F0'].append(bfformat(F0(i), 8))
        data['F0 hw'].append(hamweight(F0(i)))
    return pd.DataFrame(data)


def F1toDataFrame():
    data = {'x(2)': [], 'x hw': [], 'F1': [], 'F1 hw': []}
    for i in range(256):
        data['x(2)'].append(bfformat(i, 8))
        data['x hw'].append(hamweight(i))
        data['F1'].append(bfformat(F1(i), 8))
        data['F1 hw'].append(hamweight(F1(i)))
    return pd.DataFrame(data)


if __name__ == '__main__':
    df_F0 = F0toDataFrame()
    df_F1 = F1toDataFrame()
    distribution_of_hw_F0 = [0] * 9
    distribution_of_hw_F1 = [0] * 9
    for F0_hw in df_F0['F0 hw']:
        distribution_of_hw_F0[F0_hw] += 1
    for F1_hw in df_F1['F1 hw']:
        distribution_of_hw_F1[F1_hw] += 1
    print(distribution_of_hw_F0)
    print(distribution_of_hw_F1)
    pd.set_option("display.max.columns", None)
    df_F0.plot(x='x(2)', y='F0 hw', kind='area')
    plt.show()
    df_F1.plot(x='x(2)', y='F1 hw', kind='area')
    plt.show()

    plt.plot(distribution_of_hw_F0)
    #add_titlebox(ax2, 'Histogram: home age')
    plt.show()
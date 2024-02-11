#include <stdio.h>
#include <stdint.h>

uint32_t counter = 0;

uint32_t subtract(uint32_t x, uint32_t y) {
    while (y) {
        uint32_t borrow = (~x) & y;
        x = x ^ y;
        y = borrow << 1;
        counter+=5;
    }
    counter+=1;
    return x;
}


uint32_t inc(uint32_t i) {
    uint32_t mask = 1;
    while (i & mask) {
        i &= ~mask;
        mask <<= 1;
        counter+=5;
    }
    i |= mask;
    counter+=3;
    return i;
}

uint32_t divide(uint32_t n, uint32_t d) {
    uint32_t q = 0;
    while (n >= d) {
        uint32_t i = 0, d_t = d;
        while (n >= (d_t << 1)) {
            i = inc(i);
            d_t <<= 1;
            counter += 3;
        }
        q |= 1 << i;
        counter += 4;
        n = subtract(n, d_t);
    }
    counter += 1;
    return q;
}

uint32_t add(uint32_t x, uint32_t y) {
    while (y) {
        uint32_t carry = x & y;
        x = x ^ y;
        y = carry << 1;
        counter+=4;
    }
    counter+=1;
    return x;
}

uint32_t multiply(uint32_t a, uint32_t b) {
    uint32_t result = 0;
    while (b)
    {
        if (b & 01)
        {
            result = add(result, a);
        }
        a <<= 1;
        b >>= 1;
        counter+=4;
    }
    counter+=1;
    return result;
}

uint32_t mod(uint32_t a, uint32_t b) {
    uint32_t r = divide(a, b);
    return subtract(a, multiply(r, b));
}

uint32_t euclid (uint32_t a, uint32_t b) {
    uint32_t r;
    while (b > 0){
        r = mod(a, b);
        a = b;
        b = r;
    }
    return a;
}

int main() {
    FILE *fptr;
    fptr = fopen("output.txt","w");
    uint32_t max_counter = 0, d;
    for (uint32_t x = 1; x < 512; x++){
        for (uint32_t y = 1; y <= x; y++) {
            d = euclid(x, y);
            fprintf( fptr, "%u %u %u %u\n", x, y, counter, d);
            if (counter > max_counter) {
                max_counter = counter;
            }
            counter = 0;
        }
    }
    printf("counter = %u\n", max_counter);
    fclose(fptr);
    return 0;
}

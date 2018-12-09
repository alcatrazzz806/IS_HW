import random
import tkinter as tk
from tkinter import messagebox
from math import gcd, ceil
from hashlib import sha256

class dsa():
    def __init__(self):
        self.p = 0
        self.q = 0
        self.a = 1
        self.b = 0
        self.d = 0

    def sign(self, x):
        r, s = 0, 0
        while r == 0 or s == 0:
            kE = random.randrange(1, self.q)
            hX = int(sha256(str.encode(x)).hexdigest(), 16)
            r = pow(self.a, kE, self.p) % self.q
            s = ((hX + self.d * r)*self.egcd(self.q, kE)) % self.q
        print('r:', r)
        print('s:', s)
        return r, s

    def verify(self, x, r, s):
        hX = int(sha256(str.encode(x)).hexdigest(), 16)
        w = self.egcd(self.q, s)
        u1 = (w * hX) % self.q
        u2 = (w * r) % self.q
        v = ((pow(self.a, u1, self.p)*pow(self.b, u2, self.p)))%self.q
        rQ = (r % self.q)
        print('v:', v)
        print('r mod q:', rQ)
        return v == rQ

    def gen_key(self):
        self.q = self.gen_prime(160)
        pIsPrime = False
        while not pIsPrime:    
            x = random.getrandbits(863) << 1
            while x.bit_length() != 864:
                x = random.getrandbits(863) << 1
            self.p  = self.q * x + 1
            pIsPrime = self.miller_rabin(self.p)
        h=2
        while self.a == 1:
            self.a = pow(h, x, self.p)
            h = random.randrange(3, self.p-1)
        self.d = random.randrange(1,self.p)
        self.b = pow(self.a, self.d, self.p)
        
        print('p:', self.p)
        print('q:', self.q)
        print('a:', self.a)
        print('b:', self.b)
        
    def gen_prime(self, bits):
        isPrime = False
        while not isPrime:
            ans = random.getrandbits(bits)
            if ans.bit_length() != bits: continue
            if self.miller_rabin(ans): isPrime = True    
        return ans
    
    def miller_rabin(self, n):
        if n == 2: return True
        if n % 2 == 0: return False

        s, m = 0, n-1
        while m % 2 == 0:
            m >>= 1
            s += 1
        
        for _ in range(40):
            a = random.randrange(2, n-1)
            b = pow(a, m, n)
            if b != 1 and b != n-1:
                return False
            for _ in range(s-1):
                b = pow(b, 2, n)
                if b == n-1: break
                if b == 1: return False
            return True

    '''
    def sqr_mul(self, a, b, n):    
        ans = 1
        if 1 & b: ans = ans * a % n
        b >>= 1
        while b:
            a = pow(a,2,n)
            if 1 & b: ans = ans * a % n
            b >>= 1
        return ans
    '''

    def egcd(self, a, b):
        (x0, x1, y0, y1) = (1, 0, 0, 1)
        while b != 0:
            (q, a, b) = (a//b, b, a%b)
            (x0, x1) = (x1, x0 - q * x1)
            (y0, y1) = (y1, y0 - q * y1)
        return x0

d = dsa()
d.gen_key()
x = '123456'
r, s = d.sign(x)
print(d.verify(x, r, s))
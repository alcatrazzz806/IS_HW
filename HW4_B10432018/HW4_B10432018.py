import random
from math import gcd
#import timeit

class rsa():
    def __init__(self):
        self.p, self.q = 0, 0
        self.e, self.d = 0, 0
        self.n = 0

    def gen_key(self, bits, e=65537):
        self.gen_pq(bits)
        phi = (self.p-1) * (self.q-1)
        print('phi:', phi)
        if not e:
            e = random.randrange(3, phi-1)
            while gcd(e, phi) != 1:
                e = random.randrange(3, phi-1)
        if gcd(e, phi) != 1:
            print('e is not coprime with phi(n)!')
            return
        self.e = e
        d = self.egcd(self.e, phi)
        self.d = d if d > 0 else d + phi
        print(self.d)

    def gen_pq(self, bits):
        p_size = bits // 2
        q_size = bits - p_size

        self.p = self.gen_prime(p_size)
        self.q = self.gen_prime(q_size)
        print('p:', self.p)
        print('q:', self.q)
        self.n = self.p*self.q
        print('n:', self.n)

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

    def egcd(self, a, b):
        (x0, x1, y0, y1) = (1, 0, 0, 1)
        while b != 0:
            (q, a, b) = (a//b, b, a%b)
            (x0, x1) = (x1, x0 - q * x1)
            (y0, y1) = (y1, y0 - q * y1)
        return x0

r = rsa()
r.gen_key(1024)
#r.gen_key(1024, e=None)
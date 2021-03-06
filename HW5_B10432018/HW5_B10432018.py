import random
import tkinter as tk
from tkinter import messagebox, scrolledtext
from math import gcd, ceil
from hashlib import sha256, sha1

class dsa():
    def __init__(self):
        self.p = 0
        self.q = 0
        self.a = 1
        self.b = 0
        self.d = 0

    def sign(self, x, ke=None):
        print('Signing...')
        r, s = 0, 0
        while r == 0 or s == 0:
            kE = ke if ke else random.randrange(1, self.q)
            hX = int(sha1(str.encode(x)).hexdigest(), 16)
            r = pow(self.a, kE, self.p) % self.q
            # (self.egcd(kE, self.q)%self.q): kE's multipicative inverse under mod q
            s = ((hX + self.d * r)*(self.egcd(kE, self.q)%self.q)) % self.q
        print('kE:', kE)
        print('r:', r)
        print('s:', s)
        print()
        return kE, r, s

    def verify(self, x, r, s):
        print('Verifying...')

        # Compute auxiliary values
        hX = int(sha1(str.encode(x)).hexdigest(), 16)
        w = (self.egcd(s, self.q)%self.q)
        u1 = (w * hX) % self.q
        u2 = (w * r) % self.q
        v = ((pow(self.a, u1, self.p)*pow(self.b, u2, self.p))%self.p)%self.q
        print('v:', v)
        print('r:', r)

        # v equals r (mod q)
        if v==r:
            print('Valid!')
        else:
            print('Invalid!')
        print()
        return v

    def gen_key(self, p_bit, q_bit):
        print('Generating Key...')
        self.q = self.gen_prime(q_bit)

        # Test if prime by miller-rabin
        pIsPrime = False
        while not pIsPrime:    
            x = random.getrandbits(p_bit-q_bit-1) << 1
            while x.bit_length() != p_bit-q_bit:
                x = random.getrandbits(p_bit-q_bit-1) << 1
            # So that q divides (p - 1)   
            self.p  = self.q * x + 1
            pIsPrime = self.miller_rabin(self.p)

        # Choose alpha, d to compute beta
        # alpha: smallest positive integer such that alpha ^ q = 1 (mod p)
        self.a, h = 1, 2
        while self.a == 1:
            self.a = pow(h, x, self.p)
            h = random.randrange(3, self.p-1)
        self.d = random.randrange(1, self.q)
        self.b = pow(self.a, self.d, self.p)
        
        print('p:', self.p)
        print('q:', self.q)
        print('a:', self.a)
        print('b:', self.b)
        print('d:', self.d)
        print()

    def get_p(self):
        return self.p
    def get_q(self):
        return self.q
    def get_a(self):
        return self.a
    def get_b(self):
        return self.b
    def get_d(self):
        return self.d

    # Generate large prime number (tested with miller-rabin)
    def gen_prime(self, bits):
        isPrime = False
        while not isPrime:
            ans = random.getrandbits(bits)
            if ans.bit_length() != bits: continue
            isPrime = self.miller_rabin(ans)
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


def main():

    def genKey():
        # 1024 bits for p, 160 bits for q
        D.gen_key(1024, 160)
        p_text.delete(1.0, 'end')
        p_text.insert(1.0, D.get_p())
        q_text.delete(1.0, 'end')
        q_text.insert(1.0, D.get_q())
        a_text.delete(1.0, 'end')
        a_text.insert(1.0, D.get_a())
        b_text.delete(1.0, 'end')
        b_text.insert(1.0, D.get_b())
        d_text.delete(1.0, 'end')
        d_text.insert(1.0, D.get_d())

    def sign():
        m = m_text.get(1.0, 'end-1c')
        if not m:
            messagebox.showinfo(title='Error', message='Message is not entered!')
            return
        kE, r, s = D.sign(m)
        kE_text.delete(1.0, 'end')
        kE_text.insert(1.0, kE)
        r_text.delete(1.0, 'end')
        r_text.insert(1.0, r)
        s_text.delete(1.0, 'end')
        s_text.insert(1.0, s)

    def ver():
        m = m_text.get(1.0, 'end-1c')
        if not m:
            messagebox.showinfo(title='Error', message='Message is not entered!')
            return

        r = r_text.get(1.0, 'end-1c')
        s = s_text.get(1.0, 'end-1c')
        if not r:
            messagebox.showinfo(title='Error', message='r is not entered!')
            return
        try:
            int(r)
        except ValueError:
            messagebox.showinfo(title='Error', message='r is not decimal!')
            return
        
        if not s:
            messagebox.showinfo(title='Error', message='s is not entered!')
            return
        try:
            int(s)
        except ValueError:
            messagebox.showinfo(title='Error', message='s is not decimal!')
            return
        
        v = D.verify(m, int(r), int(s))
        v_text.delete(1.0, 'end')
        v_text.insert(1.0, v)
        if v == int(r):
            messagebox.showinfo(title='Valid', message='This message is valid!')
        else:
            messagebox.showinfo(title='Invalid', message='This message is invalid!')

    # Main function starts here
    D = dsa()
    root = tk.Tk()
    root.title('DSA_B10432018')
    root.geometry('720x600')

    canvas = tk.Canvas(root, width=720, height=900, scrollregion=(0,0,720,950))
    frame = tk.Frame(canvas)
    frame.place(width=720, height=900)
    
    scrollbar = tk.Scrollbar(canvas)
    scrollbar.pack(side='right', fill='y')
    canvas.create_window((350,470), window=frame)
    canvas.pack(side='left', fill='both', expand='True')
    scrollbar.configure(command=canvas.yview)
    canvas.config(yscrollcommand=scrollbar.set)

    p_label = tk.Label(frame, text='p')
    q_label = tk.Label(frame, text='q')
    a_label = tk.Label(frame, text='a')
    b_label = tk.Label(frame, text='b')
    d_label = tk.Label(frame, text='d')
    m_label = tk.Label(frame, text='Message')
    kE_label = tk.Label(frame, text='kE')
    r_label = tk.Label(frame, text='r')
    s_label = tk.Label(frame, text='s')
    v_label = tk.Label(frame, text='v')

    p_text = scrolledtext.ScrolledText(frame, width=96, height=2)
    q_text = scrolledtext.ScrolledText(frame, width=96, height=1)
    a_text = scrolledtext.ScrolledText(frame, width=96, height=1)
    b_text = scrolledtext.ScrolledText(frame, width=96, height=1)
    d_text = scrolledtext.ScrolledText(frame, width=96, height=1)
    m_text = scrolledtext.ScrolledText(frame, width=96, height=1)
    kE_text = scrolledtext.ScrolledText(frame, width=96, height=1)
    r_text = scrolledtext.ScrolledText(frame, width=96, height=1)
    s_text = scrolledtext.ScrolledText(frame, width=96, height=1)
    v_text = scrolledtext.ScrolledText(frame, width=96, height=1)

    signButton = tk.Button(frame, text='Sign', width=20, command=sign)
    verButton = tk.Button(frame, text='Verify', width=20, command=ver)
    genButton = tk.Button(frame, text='Gen Key', width=20, command=genKey)

    p_label.pack(pady=3)
    p_text.pack(pady=3)

    q_label.pack(pady=3)
    q_text.pack(pady=3)

    a_label.pack(pady=3)
    a_text.pack(pady=3)

    b_label.pack(pady=3)
    b_text.pack(pady=3)

    d_label.pack(pady=3)
    d_text.pack(pady=3)

    m_label.pack(pady=3)
    m_text.pack(pady=3)

    kE_label.pack(pady=3)
    kE_text.pack(pady=3)

    r_label.pack(pady=3)
    r_text.pack(pady=3)

    s_label.pack(pady=3)
    s_text.pack(pady=3)
    v_label.pack(pady=3)
    v_text.pack(pady=3)

    verButton.pack(side='bottom', pady=5)
    signButton.pack(side='bottom', pady=5)
    genButton.pack(side='bottom', pady=5)

    root.mainloop()

if __name__ == "__main__":
    main()

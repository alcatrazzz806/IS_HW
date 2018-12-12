import random
import tkinter as tk
from tkinter import messagebox, scrolledtext
from math import gcd, ceil

class RSAError(Exception):
    pass
class PrimalityError(RSAError):
    pass

class rsa():
    def __init__(self):
        self.p, self.q = 0, 0
        self.e, self.d = 0, 0
        self.dp, self.dq, self.qinv = 0, 0, 0
        self.n = 0

    def encrypt(self, plaintext):
        #pt = int(plaintext, 16)
        ans = self.sqr_mul(plaintext, self.e, self.n)
        return ans #f'{ans:0>{ceil(ans.bit_length()/8)}X}'
    
    def decrypt(self, ciphertext):
        #ct = int(ciphertext, 16)
        m1 = self.sqr_mul(ciphertext, self.dp, self.p)
        m2 = self.sqr_mul(ciphertext, self.dq, self.q)
        h = (self.qinv * (m1-m2)) % self.p
        ans = m2 + h * self.q
        return ans #f'{ans:0>{ceil(ans.bit_length()/8)}X}'

    def set_key(self, p, q, e=65537):     
        self.p, self.q, self.n = p, q, p*q
        if self.p < self.q: self.p, self.q = self.q, self.p
        phi = (self.p-1) * (self.q-1)

        if not e:
            e = random.randrange(3, phi-1)
            while gcd(e, phi) != 1:
                e = random.randrange(3, phi-1)

        if gcd(e, phi) != 1:
            raise PrimalityError        
        self.e = e
        d = self.egcd(self.e, phi)
        self.d = d if d > 0 else d + phi
        self.dp, self.dq = self.d % (self.p-1), self.d % (self.q-1)
        self.qinv = self.egcd(self.q, self.p) % (self.p)
        
        '''
        #print('p:', f'{self.p:0>{ceil(self.p.bit_length()/8)}X}')
        #print('q:', f'{self.q:0>{ceil(self.q.bit_length()/8)}X}')
        #print('n:', f'{self.n:0>{ceil(self.n.bit_length()/8)}X}')
        #print('phi:', f'{phi:0>{ceil(phi.bit_length()/8)}X}')
        #print('e:', f'{self.e:0>{ceil(self.e.bit_length()/8)}X}')
        #print('d:', f'{self.d:0>{ceil(self.d.bit_length()/8)}X}')
        #print('dp:', f'{self.dp:0>{ceil(self.dp.bit_length()/8)}X}')
        #print('dq:', f'{self.dq:0>{ceil(self.dq.bit_length()/8)}X}')
        #print('qinv:', f'{self.qinv:0>{ceil(self.qinv.bit_length()/8)}X}','\n')
        '''

    def set_public(self, n, e):
        self.n = n
        self.e = e

    def gen_key(self, bits, e=65537):
        self.gen_pq(bits)
        self.n = self.p*self.q
        phi = (self.p-1) * (self.q-1)
        if not e:
            e = random.randrange(3, phi-1)
            while gcd(e, phi) != 1:
                e = random.randrange(3, phi-1)
        while gcd(e, phi) != 1:
            self.gen_pq(bits)
            self.n = self.p*self.q
            phi = (self.p-1) * (self.q-1)
        self.e = e
        d = self.egcd(self.e, phi)
        self.d = d if d > 0 else d + phi
        self.dp, self.dq = self.d % (self.p-1), self.d % (self.q-1)
        self.qinv = self.egcd(self.q, self.p) % (self.p)

        '''
        #print('p:', f'{self.p:0>{ceil(self.p.bit_length()/8)}X}')
        #print('q:', f'{self.q:0>{ceil(self.q.bit_length()/8)}X}')
        #print('n:', f'{self.n:0>{ceil(self.n.bit_length()/8)}X}')
        #print('phi:', f'{phi:0>{ceil(phi.bit_length()/8)}X}')
        #print('e:', f'{self.e:0>{ceil(self.e.bit_length()/8)}X}')
        #print('d:', f'{self.d:0>{ceil(self.d.bit_length()/8)}X}')
        #print('dp:', f'{self.dp:0>{ceil(self.dp.bit_length()/8)}X}')
        #print('dq:', f'{self.dq:0>{ceil(self.dq.bit_length()/8)}X}')
        #print('qinv:', f'{self.qinv:0>{ceil(self.qinv.bit_length()/8)}X}','\n')
        '''

    def get_p(self):
        return self.p #f'{self.p:0>{ceil(self.p.bit_length()/8)}X}'
    def get_q(self):
        return self.q #f'{self.q:0>{ceil(self.q.bit_length()/8)}X}'
    def get_n(self):
        return self.n #f'{self.n:0>{ceil(self.n.bit_length()/8)}X}'
    def get_e(self):
        return self.e #f'{self.e:0>{ceil(self.e.bit_length()/8)}X}'
    def get_d(self):
        return self.d #f'{self.d:0>{ceil(self.d.bit_length()/8)}X}'
    def get_dp(self):
        return self.dp #f'{self.dp:0>{ceil(self.dp.bit_length()/8)}X}'
    def get_dq(self):
        return self.dq #f'{self.dq:0>{ceil(self.dq.bit_length()/8)}X}'
    def get_qinv(self):
        return self.qinv #f'{self.qinv:0>{ceil(self.qinv.bit_length()/8)}X}'

    def gen_pq(self, bits):
        p_size = bits // 2
        q_size = bits - p_size

        self.p = self.gen_prime(p_size)
        self.q = self.gen_prime(q_size)
        if self.p < self.q: self.p, self.q = self.q, self.p

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
            b = self.sqr_mul(a, m, n)
            if b != 1 and b != n-1:
                return False
            for _ in range(s-1):
                b = self.sqr_mul(b, 2, n)
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

    def sqr_mul(self, a, b, n):
        ans = 1
        if 1 & b: ans = ans * a % n
        b >>= 1
        while b:
            a = pow(a,2,n)
            if 1 & b: ans = ans * a % n
            b >>= 1
        return ans

def main():
    def genKey():
        gen = False
        bits = length_text.get(1.0, 'end-1c')
        if not bits.isnumeric() or int(bits) <= 0:
            messagebox.showinfo(title='Error', message='Length is not numeric or too small!')
            length_text.delete(1.0, 'end')
            length_text.insert(1.0, '1024')
            bits = length_text.get(1.0, 'end-1c')
        e = e_text.get(1.0, 'end-1c')
        if e:
            try:
                int(e, 16)
                try:
                    r.gen_key(int(bits), e=int(e))
                    gen = True
                except PrimalityError:
                    messagebox.showinfo(title='Error', message='e is not coprime with phi!')
                    return
            except ValueError:
                messagebox.showinfo(title='Error', message='e is not hexadecimal!')
                return
        if not gen: r.gen_key(int(bits), e=None)
        p_text.delete(1.0, 'end')
        p_text.insert(1.0, r.get_p())
        q_text.delete(1.0, 'end')
        q_text.insert(1.0, r.get_q())
        n_text.delete(1.0, 'end')
        n_text.insert(1.0, r.get_n())
        e_text.delete(1.0, 'end')
        e_text.insert(1.0, r.get_e())
        d_text.delete(1.0, 'end')
        d_text.insert(1.0, r.get_d())
        dp_text.delete(1.0, 'end')
        dp_text.insert(1.0, r.get_dp())
        dq_text.delete(1.0, 'end')
        dq_text.insert(1.0, r.get_dq())
        qinv_text.delete(1.0, 'end')
        qinv_text.insert(1.0, r.get_qinv())

    def setKey():
        gen = False
        p = p_text.get(1.0, 'end-1c')
        q = q_text.get(1.0, 'end-1c')
        if not p or not q:
            messagebox.showinfo(title='Error', message='p or q is not entered!')
            return
        try:
            int(p)
            int(q)
        except ValueError:
            messagebox.showinfo(title='Error', message='p or q is not decimal!')
            return
        
        e = e_text.get(1.0, 'end-1c')
        if e:
            try:
                int(e)
                try:
                    r.set_key(int(p), int(q), e=int(e))
                    gen = True
                except PrimalityError:
                    messagebox.showinfo(title='Error', message='e is not coprime with phi!')
                    return
            except ValueError:
                messagebox.showinfo(title='Error', message='e is not decimal!')
                return
        if not gen: r.set_key(int(p), int(q), e=None)
        p_text.delete(1.0, 'end')
        p_text.insert(1.0, r.get_p())
        q_text.delete(1.0, 'end')
        q_text.insert(1.0, r.get_q())
        n_text.delete(1.0, 'end')
        n_text.insert(1.0, r.get_n())
        e_text.delete(1.0, 'end')
        e_text.insert(1.0, r.get_e())
        d_text.delete(1.0, 'end')
        d_text.insert(1.0, r.get_d())
        dp_text.delete(1.0, 'end')
        dp_text.insert(1.0, r.get_dp())
        dq_text.delete(1.0, 'end')
        dq_text.insert(1.0, r.get_dq())
        qinv_text.delete(1.0, 'end')
        qinv_text.insert(1.0, r.get_qinv())
        length_text.delete(1.0, 'end')
        length_text.insert(1.0, len(bin(r.get_n()))-2)

    def enc():
        n = n_text.get(1.0, 'end-1c')
        if not n:
            messagebox.showinfo(title='Error', message='n is not entered!')
            return
        try:
            int(n)
        except ValueError:
            messagebox.showinfo(title='Error', message='n is not decimal!')
            return

        e = e_text.get(1.0, 'end-1c')
        if not e:
            messagebox.showinfo(title='Error', message='e is not entered!')
            return
        try:
            int(e)
        except ValueError:
            messagebox.showinfo(title='Error', message='e is not decimal!')
            return
        
        r.set_public(int(n), int(e))

        pt = pt_text.get(1.0, 'end-1c')
        if not pt:
            messagebox.showinfo(title='Error', message='Plaintext is not entered!')
            return
        try:
            int(pt)
        except ValueError:
            messagebox.showinfo(title='Error', message='Plaintext is not decimal!')
            return
        ct = r.encrypt(int(pt))
        ct_text.delete(1.0, 'end')
        ct_text.insert(1.0, ct)

    def dec():
        ct = ct_text.get(1.0, 'end-1c')
        if not ct:
            messagebox.showinfo(title='Error', message='Ciphertext is not entered!')
            return
        try:
            int(ct)
        except ValueError:
            messagebox.showinfo(title='Error', message='Ciphertext is not decimal!')
            return
        pt = r.decrypt(int(ct))
        pt_text.delete(1.0, 'end')
        pt_text.insert(1.0, pt)

    r = rsa()
    root = tk.Tk()
    root.title('RSA_B10432018')
    root.geometry('720x600')

    canvas = tk.Canvas(root, width=720, height=1000, scrollregion=(0,0,720,1100))
    frame = tk.Frame(canvas)
    frame.place(width=720, height=940)
    
    scrollbar = tk.Scrollbar(canvas)
    scrollbar.pack(side='right', fill='y')
    canvas.create_window((350,550), window=frame)
    canvas.pack(side='left', fill='both', expand='True')
    scrollbar.configure(command=canvas.yview)
    canvas.config(yscrollcommand=scrollbar.set)
    #frame.config(yscrollcommand=scrollbar.set)

    p_label = tk.Label(frame, text='p')
    q_label = tk.Label(frame, text='q')
    n_label = tk.Label(frame, text='n')
    e_label = tk.Label(frame, text='e')
    d_label = tk.Label(frame, text='d')
    dp_label = tk.Label(frame, text='dp')
    dq_label = tk.Label(frame, text='dq')
    qinv_label = tk.Label(frame, text='qinv')
    length_lable = tk.Label(frame, text='Bit Length')
    pt_label = tk.Label(frame, text='Plaintext')
    ct_label = tk.Label(frame, text='Ciphertext')

    p_text = scrolledtext.ScrolledText(frame, width=96, height=2)
    q_text = scrolledtext.ScrolledText(frame, width=96, height=2)
    n_text = scrolledtext.ScrolledText(frame, width=96, height=4)
    e_text = scrolledtext.ScrolledText(frame, width=96, height=2)
    d_text = scrolledtext.ScrolledText(frame, width=96, height=4)
    dp_text = scrolledtext.ScrolledText(frame, width=96, height=2)
    dq_text = scrolledtext.ScrolledText(frame, width=96, height=2)
    qinv_text = scrolledtext.ScrolledText(frame, width=96, height=2)
    length_text = scrolledtext.ScrolledText(frame, width=96, height=1)
    pt_text = scrolledtext.ScrolledText(frame, width=96, height=4)
    ct_text = scrolledtext.ScrolledText(frame, width=96, height=4)
    e_text.insert(1.0, chars='65537')
    length_text.insert(1.0, chars='1024')

    decButton = tk.Button(frame, text='Decrypt', width=20, command=dec)
    encButton = tk.Button(frame, text='Encrypt', width=20, command=enc)
    genButton = tk.Button(frame, text='Gen Key', width=20, command=genKey)
    setButton = tk.Button(frame, text='Set Key', width=20, command=setKey)

    p_label.pack(pady=3)
    p_text.pack(pady=3)

    q_label.pack(pady=3)
    q_text.pack(pady=3)

    n_label.pack(pady=3)
    n_text.pack(pady=3)

    e_label.pack(pady=3)
    e_text.pack(pady=3)

    d_label.pack(pady=3)
    d_text.pack(pady=3)

    dp_label.pack(pady=3)
    dp_text.pack(pady=3)

    dq_label.pack(pady=3)
    dq_text.pack(pady=3)

    qinv_label.pack(pady=3)
    qinv_text.pack(pady=3)

    length_lable.pack(pady=3)
    length_text.pack(pady=3)

    pt_label.pack(pady=3)
    pt_text.pack(pady=3)
    ct_label.pack(pady=3)
    ct_text.pack(pady=3)

    decButton.pack(side='bottom', pady=5)
    encButton.pack(side='bottom', pady=5)
    genButton.pack(side='bottom', pady=5)
    setButton.pack(side='bottom', pady=5)

    root.mainloop()

if __name__ == "__main__":
    main()

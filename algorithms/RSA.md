# RSA Algorithm

### Step-by-Step Process

1. **Choose Two Prime Numbers (p, q)**:
    - The user is prompted to enter two prime numbers `p` and `q`.
  
2. **Compute n and Totient (φ(n))**:
    - Compute `n`:
      \[
      n = p \times q
      \]
    - Compute Euler's Totient function `φ(n)`:
      \[
      \phi(n) = (p - 1) \times (q - 1)
      \]
    - The value of `n` is used for both the public and private keys, while `φ(n)` is used to calculate the private key.

3. **Find Public Key Exponent (e)**:
    - Find a value `e` such that:
      \[
      1 < e < \phi(n) \quad \text{and} \quad \text{gcd}(e, \phi(n)) = 1
      \]
    - This ensures that `e` is coprime with `φ(n)`.

4. **Find Private Key Exponent (d)**:
    - Compute `d` such that:
      \[
      (d \times e) \mod \phi(n) = 1
      \]
    - This means that `d` is the modular multiplicative inverse of `e` modulo `φ(n)`.

5. **Generate Public and Private Keys**:
    - The public key is the pair `(e, n)`.
    - The private key is the pair `(d, n)`.

6. **Encryption**:
    - The plaintext message `m` is converted to a `BigInteger`.
    - The ciphertext `C` is computed using the public key `(e, n)` as:
      \[
      C = m^e \mod n
      \]
    - This is performed using the modular exponentiation technique (`modPow`).

7. **Decryption**:
    - The ciphertext `C` is decrypted using the private key `(d, n)` as:
      \[
      m' = C^d \mod n
      \]
    - This process recovers the original plaintext message.

8. **Output**:
    - The public key `(e, n)`, private key `(d, n)`, ciphertext `C`, and the decrypted message `m'` are printed.

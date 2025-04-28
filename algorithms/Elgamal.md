# ElGamal Encryption Algorithm

### Steps:

#### 1. **Key Generation**:
   - Select a large prime number \( p \) and a primitive root modulo \( p \), \( g \).
   - Choose a private key \( x \), where \( 1 \leq x \leq p-2 \).
   - Compute the public key \( y \) as:
     \[
     y = g^x \mod p
     \]
   - The private key is \( x \), and the public key is \( (p, g, y) \).

#### 2. **Encryption** (Using Public Key):
   - The sender wants to send a message \( m \) to the recipient.
   - Choose a random ephemeral key \( k \), where \( 1 \leq k \leq p-2 \).
   - Compute the two cipher components:
     - \( c_1 = g^k \mod p \)
     - \( c_2 = (m \cdot y^k) \mod p \)
   - The ciphertext consists of the pair \( (c_1, c_2) \), which can be packed into a single integer as:
     \[
     \text{ciphertext} = (c_1 \ll 16) | c_2
     \]
     where \( \ll \) denotes bit-shifting and `|` is the bitwise OR operator.

#### 3. **Decryption** (Using Private Key):
   - The recipient, who knows the private key \( x \), receives the ciphertext \( (c_1, c_2) \).
   - Extract \( c_1 \) and \( c_2 \) from the packed ciphertext:
     \[
     c_1 = \text{cipher} \gg 16 \quad \text{and} \quad c_2 = \text{cipher} \& 0xFFFF
     \]
   - Compute the shared secret \( s = c_1^x \mod p \).
   - Calculate the modular inverse of \( s \) using Fermat's Little Theorem:
     \[
     s^{-1} = s^{p-2} \mod p
     \]
   - Recover the original message \( m \) by:
     \[
     m = (c_2 \cdot s^{-1}) \mod p
     \]

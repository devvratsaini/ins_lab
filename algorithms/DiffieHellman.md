# Diffie-Hellman Key Exchange Algorithm

### Step-by-Step Process

1. **Choose Prime Number and Primitive Root**:
    - Choose a large prime number `p` (this is publicly known).
    - Choose a primitive root modulo `p` (denoted as `g`), which is also publicly known.

2. **Generate Private Keys**:
    - Each participant generates their private key:
        - You choose a private key `a`.
        - Your partner chooses a private key `b`.

3. **Generate Public Keys**:
    - Each participant generates their public key by computing:
        - Your public key `A` is computed as:
            \[
            A = g^a \mod p
            \]
        - Your partner's public key `B` is computed as:
            \[
            B = g^b \mod p
            \]
    - Both public keys `A` and `B` are exchanged between the participants.

4. **Compute Shared Secret**:
    - Each participant computes the shared secret by using the other participant’s public key and their own private key:
        - You compute the shared secret `S_A` as:
            \[
            S_A = B^a \mod p
            \]
        - Your partner computes the shared secret `S_B` as:
            \[
            S_B = A^b \mod p
            \]
    - Both participants now have the same shared secret, `S_A` and `S_B`, which will be used for further communication.

5. **Confirm Shared Secret**:
    - Since both `S_A` and `S_B` are equal, both parties now share the same secret key, which can be used for encryption and decryption in further communication.

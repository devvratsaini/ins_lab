# Rail Fence Cipher

## 1. Preprocess Plaintext:
- Converts the input plaintext to uppercase and removes any non-alphabetical characters.

## 2. Encryption Process:
- Creates a matrix with `key` rows and `plaintext.length()` columns.
- The plaintext characters are written diagonally in a **"zigzag"** pattern (top-down, then bottom-up).
- The ciphertext is extracted row by row.

## 3. Decryption Process:
- Constructs a matrix with the same dimensions as during encryption.
- Marks the positions where the ciphertext will go.
- The ciphertext is inserted into the matrix in these marked positions.
- The plaintext is read row by row from the matrix.

## Key Steps:
- **Encryption**: Fill the matrix diagonally and extract it row-wise to get ciphertext.
- **Decryption**: Reconstruct the zigzag pattern and read it to get the original plaintext.

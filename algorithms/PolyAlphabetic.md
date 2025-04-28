# PolyAlphabetic Cipher Algorithm

## 1. Matrix Creation
- Construct a 26x26 matrix where each cell contains a letter based on the sum of its row and column indices modulo 26.

## 2. Preprocess Plaintext
- Convert plaintext to uppercase and remove any non-alphabetical characters.

## 3. Encryption
- For each character in the plaintext:
  - Use the key to determine the row (from the key) and the column (from the plaintext).
  - The character at the corresponding position in the matrix becomes part of the ciphertext.

## 4. Decryption
- For each character in the ciphertext:
  - Use the key to determine the row (from the key) and find the column where the ciphertext character exists in the matrix.
  - The character at that column index (in the plaintext row) becomes part of the decrypted text.

## Main Flow
1. **Matrix Creation**: A 26x26 matrix is created with cyclically shifted alphabet rows.
2. **Key and Plaintext Input**: User inputs key and plaintext.
3. **Encryption**: The plaintext is encrypted using the PolyAlphabetic cipher matrix.
4. **Decryption**: The ciphertext is decrypted back to plaintext using the same matrix.
5. **Display Results**: Display both the ciphertext and the decrypted text.

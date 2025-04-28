# Hill Cipher Algorithm

## 1. Preprocess the Text
- Convert the text to uppercase and remove any non-alphabetical characters.

## 2. Key Matrix Generation
- The key is a 9-character string.
- Create a 3x3 matrix from the key by mapping each character to its position in the alphabet (A = 0, B = 1, ..., Z = 25).
- The matrix is filled row by row.

## 3. Encryption
- **Input:** Preprocessed plaintext and key.
- **Step 1:** If the length of the plaintext is not divisible by 3, pad it with 'X' characters to make it a multiple of 3.
- **Step 2:** Split the plaintext into blocks of 3 characters.
- **Step 3:** Convert each block into a 3x1 vector (numeric values based on the alphabet).
- **Step 4:** Multiply the 3x3 key matrix by the 3x1 vector (modulo 26) to obtain a 3x1 ciphertext vector.
- **Step 5:** Convert the ciphertext vector back into characters to form the ciphertext.
  
## 4. Decryption
- **Input:** Ciphertext and key.
- **Step 1:** Compute the determinant of the key matrix modulo 26.
- **Step 2:** Calculate the modular inverse of the determinant.
- **Step 3:** Find the adjoint of the key matrix by computing the cofactors and transposing them.
- **Step 4:** Multiply each element of the adjoint matrix by the modular inverse of the determinant (modulo 26) to get the inverse of the key matrix.
- **Step 5:** Split the ciphertext into blocks of 3 characters.
- **Step 6:** Convert each ciphertext block into a 3x1 vector.
- **Step 7:** Multiply the 3x3 inverse key matrix by the ciphertext vector (modulo 26) to obtain the plaintext vector.
- **Step 8:** Convert the plaintext vector back into characters to form the decrypted plaintext.

## 5. Key Steps Summary:
- **Encryption**: Multiply plaintext blocks by the key matrix to get ciphertext.
- **Decryption**: Multiply ciphertext blocks by the inverse of the key matrix to retrieve the original plaintext.

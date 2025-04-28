# Playfair Cipher Algorithm

## 1. Matrix Creation
- Convert the key to uppercase and remove duplicates and 'J'.
- Add remaining letters of the alphabet (A-Z excluding 'J').
- Create a 5x5 matrix using the key and remaining alphabet.

## 2. Preprocess Plaintext
- Convert to uppercase and remove non-alphabetic characters.
- Replace 'J' with 'I'.
- Split plaintext into digraphs, adding 'X' between duplicate letters or at the end if odd length.

## 3. Encryption
- For each digraph:
  - Same row: shift characters right.
  - Same column: shift characters down.
  - Rectangle: swap opposite corners.

## 4. Decryption
- For each digraph:
  - Same row: shift characters left.
  - Same column: shift characters up.
  - Rectangle: swap opposite corners.
- Remove padding 'X'.

## Main Flow
1. Take key and plaintext as input.
2. Generate matrix and preprocess plaintext.
3. Encrypt plaintext and decrypt the ciphertext.
4. Display results.

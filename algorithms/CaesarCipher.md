# Caesar Cipher Algorithm

## 1. Caesar Cipher Encryption

### Input:
- `plaintext` (the text to be encrypted)
- `key` (the number of positions each letter in the plaintext will be shifted)

### Steps:

1. Initialize an empty string `ciphertext`.
2. For each character `ch` in `plaintext`:
    - If the character is an uppercase letter (`A-Z`):
        1. Calculate the new character by shifting it by `key` positions.
        2. The new position is:  
           `new_char = ('A' + (ch - 'A' + key) % 26)`
    - If the character is a lowercase letter (`a-z`):
        1. Calculate the new character by shifting it by `key` positions.
        2. The new position is:  
           `new_char = ('a' + (ch - 'a' + key) % 26)`
    - If the character is non-alphabetic (like spaces, punctuation), append it directly to `ciphertext` without modification.
3. Return the `ciphertext` as the result.

---

## 2. Caesar Cipher Decryption

### Input:
- `ciphertext` (the text to be decrypted)
- `key` (the number of positions each letter in the ciphertext is shifted)

### Steps:

1. Initialize an empty string `plaintext`.
2. For each character `ch` in `ciphertext`:
    - If the character is an uppercase letter (`A-Z`):
        1. Calculate the new character by shifting it backwards by `key` positions.
        2. The new position is:  
           `new_char = ('A' + (ch - 'A' - key + 26) % 26)`  
           (The `+26` ensures a positive result even if the subtraction results in a negative number.)
    - If the character is a lowercase letter (`a-z`):
        1. Calculate the new character by shifting it backwards by `key` positions.
        2. The new position is:  
           `new_char = ('a' + (ch - 'a' - key + 26) % 26)`
    - If the character is non-alphabetic (like spaces, punctuation), append it directly to `plaintext` without modification.
3. Return the `plaintext` as the result.

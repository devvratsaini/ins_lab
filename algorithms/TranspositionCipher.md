# Transposition Cipher Algorithm

### **1. Preprocess the Plaintext:**
   - Convert all characters in the plaintext to uppercase.
   - Remove any non-alphabetic characters (spaces, punctuation, etc.).

### **2. Encryption Process:**
   - **Input:** A string `plaintext` and a string `key`.
   - **Step 1:** Compute the number of columns in the matrix, which is the length of the `key`.
   - **Step 2:** Calculate the number of rows needed to fit the plaintext in the matrix. This is done by dividing the plaintext length by the number of columns, rounding up to ensure all characters fit.
   - **Step 3:** Create an empty 2D matrix of size `rows x cols`.
   - **Step 4:** Fill the matrix row by row with characters from the plaintext.
   - **Step 5:** If the plaintext doesn't fill the matrix completely, pad the remaining cells with spaces.
   - **Step 6:** Using the `key`, reorder the columns of the matrix. The `key` provides the column order for reading the matrix.
   - **Step 7:** Concatenate the columns in the order specified by the `key` to generate the ciphertext.

### **3. Decryption Process:**
   - **Input:** A string `ciphertext` and a string `key`.
   - **Step 1:** Compute the number of columns in the matrix, which is the length of the `key`.
   - **Step 2:** Calculate the number of rows based on the ciphertext length.
   - **Step 3:** Create an empty 2D matrix of size `rows x cols`.
   - **Step 4:** Using the `key`, place the characters from the ciphertext into the matrix column by column, as per the order specified by the `key`.
   - **Step 5:** Read the matrix row by row to get the decrypted plaintext.
   - **Step 6:** Trim any extra spaces that were added during the encryption padding.
   - **Step 7:** Return the decrypted plaintext.

# Basic Hashing Algorithm with Chaining and Linear Probing

### Algorithm Breakdown:

1. **Initialize Tables**:
   - Create two hash tables:
     - `chainingTable`: An array of linked lists to handle collisions with chaining.
     - `linearProbingTable`: An array of integers for linear probing.
   - Set table size to 10 (`SIZE = 10`).

2. **Hash Function**:
   - A simple modulo function is used to compute the hash value:
     \[
     \text{index} = \text{key} \% \text{SIZE}
     \]

3. **Insertion (Chaining)**:
   - For each key:
     1. Compute the hash index.
     2. If no list exists at the index, create a new list.
     3. Add the key to the list at the computed index.

4. **Insertion (Linear Probing)**:
   - For each key:
     1. Compute the hash index.
     2. If the slot at the computed index is empty, insert the key.
     3. If the slot is occupied, probe the next index until an empty slot is found (circular probing).

5. **Display Hash Table (Chaining)**:
   - Iterate through each index of the chaining table.
   - Print the list of keys stored at each index.

6. **Display Hash Table (Linear Probing)**:
   - Iterate through each index of the linear probing table.
   - Print the key at each index (or `null` if empty).

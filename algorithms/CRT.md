# Chinese Remainder Theorem Algorithm

### Steps:

1. **Calculate the product of all divisors:**
   \[
   P = n_1 \times n_2 \times \dots \times n_k
   \]
   This is the total product of all divisors.

2. **For each equation, calculate the partial product `pp`:**
   \[
   pp = \frac{P}{n_i} \quad \text{for each divisor} \ n_i
   \]
   Here `pp` is the product of all divisors except for `n_i`.

3. **Find the modular inverse of the partial product `pp` modulo `n_i`:**
   To find the modular inverse, we use the Extended Euclidean Algorithm to solve:
   \[
   pp \times inv \equiv 1 \ (\text{mod} \ n_i)
   \]
   Where `inv` is the modular inverse of `pp` modulo `n_i`.

4. **Calculate the term for each equation:**
   For each equation, calculate the term contribution to the final result:
   \[
   \text{Term}_i = r_i \times inv \times pp
   \]
   Where `r_i` is the remainder of the equation.

5. **Sum the terms:**
   Add all the terms to get the result before modulo operation:
   \[
   \text{Result} = \sum_{i=1}^{k} \text{Term}_i
   \]

6. **Take the result modulo the total product `P`:**
   \[
   x = \text{Result} \ (\text{mod} \ P)
   \]
   This gives the solution to the system of congruences.


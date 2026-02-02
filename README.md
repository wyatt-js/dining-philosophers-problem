# Longiy Tsin & Wyatt Smith
### Assignment Directions:

* Using Java, and threads in Java, program a solution to the Dining Philosophers problem, a classic problem in concurrent computation. Use judicious output to show what is going on in your simulation as it executes. Make sure your solution avoids deadlocks, starvations, and race conditions.

* In your readme file for the zipfile, explain your design rationale. What features and structures are you using to represent the philosophers? To represent the table, the forks, the spaghetti? To represent eating phase of a philosophers "life"... the thinking phase?

* What does your algorithm do to help prevent deadlocks and starvation? Are deadlocks and/or starvations still possible (and just improbable)?

<img width="824" height="615" alt="Screenshot 2026-01-22 at 14 44 35" src="https://github.com/user-attachments/assets/d81f9499-dc03-4e46-ad2e-424f66d4983e" />

# Dining Philosophers Problem — Java Project

## Overview
This project is a Java implementation of the **Dining Philosophers Problem**. The main goal was to solve the problem correctly while improving our understanding of **Java threads, locks, and synchronization**.

We focused on avoiding common concurrency problems such as **deadlock**, **race conditions**, and **starvation**.

---

## Project Structure

### Fork
The `Fork` class represents a shared resource.
- Each fork has a unique ID.
- Each fork uses a `ReentrantLock` so only one philosopher can use it at a time.

---

### Table (Waiter)
The `Table` class controls access to forks.
- It keeps a queue of philosophers to make sure everyone gets a fair turn.
- Philosophers must wait until it is their turn and both forks are available.
- This design helps prevent deadlock and starvation.

---

### Philosopher
The `Philosopher` class extends `Thread`.
- A philosopher thinks, asks the table for forks, eats, then releases the forks.
- Philosophers never interact with forks directly — everything goes through the table.

---

### Main
The `Main` class starts the program.
- It creates one shared table.
- It creates and starts all philosopher threads.

---

## What This Solves
- Deadlocks:
  - Deadlocks are not possible due to our implementation of the `Table` class which manages a philosopher's access to forks through a queue. They may only acquire forks when they are at the front of the queue and can acquire both forks. This prevents the situation where a philosopher holds one fork while waiting on another. Additionally, forks are always put down at some point, so there is no circular dependency between resources.
- Race conditions:
  - Race conditions are prevented through the waiterLock which ensures proper management of fork availability, preventing philosophers from accessing the same resource.
- Starvations:
  - Starvations are improbable. The implementation includes a queue with signalAll() so that ordering is enforced and every philosopher has a chance to eat.

---

## Summary
This project helped us practice Java concurrency by using locks, threads, and a queue-based solution to safely solve the Dining Philosophers Problem.

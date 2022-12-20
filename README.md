# AdventOfCode
I decided to share some of my solutions for the advent of code challenges. Started a little late so I'm a bit behind, but I do plan on finishing the majority of them.

I am working exclusively with Kotlin here, and I'm treating each challenge as if they were interview questions. This means I'm coding off the top of my head just using the IDE, really keeping the googling to a minimum. Also trying to finishing each part in one sitting. Definitely looking to leverage language features where possible, but trying to keep it straightforward without adding any dependencies.

Each package corresponds directly to that day's challenge. With each challenge I am trying to build out at least some practical domain language to describe the solution (not just banging it out in an unreadable one liner). My goal there is to have some reasonable code that is pretty easy to follow. Another goal would be to revisit this repo in a few months and still be able to understand my own code. We'll see :D

___

### My general approach:

1. Copy in the sample data from the description, set it up in a package with it's own main classes starting with my template files. 
3. Build out a few primitives into typealiases for sanity to try and approximate which data structures I'm going to need.
4. Create a parser that will break up the raw strings into my desired data models.
5. Create a logical layer that processes the data models into the desired output.
6. Adding a simple reducer or summation call to figure out the end result of part one.
7. Refactoring a bit for clarity and reusability and then introducing a second main for part two.
8. Adding new processing features for part two (hopefully without any headaches) ;)

___

#### *Notes:*
  - I'm intending for each day's solution to be entirely standalone so there is definitely cross-package duplication
  - Part 1 and part 2 will share most of the code but have seperately runnable `main()`'s (should run fine in IntelliJ)
  - While I am a huge fan of TDD in the workplace, I'm depending mainly on the debugger and simple print statements for validations.
    - *I may unit test this code a bit on some solutions in the future, as needed, or if I decide to document anything*

___

#### Feel free to reach out in the discussions section if you have any questions! 
 

## Fawry Challenge 2025
this is a submission for the Fawry challenge for the full stack internship 2025

## Assumptions
### Products class

- For simplicity, I assumed that the name of the product will be unique and use a HashMap to store cart information to avoid duplicates of the same product
- For items that won't be shipped, they be added tp the recipt and can be recieved at the store.
- Since products can have an expiry date, be shipped or both, this could lead to multiple hierirachial inheritance that will further complicate creating products

**Solution**: Switch from inheritance to composition for the expiration and shipping, making it easier to add more attributes without complexity

## Dependencies Used
- **Lombok:** Used to reduce code by automatically generating getters, setters, constructors, and other common methods through annotations. This keeps the code clean and concise, especially for attributes that do not require custom validation.
- **JUnit:** Used as the testing framework for writing and running unit tests.
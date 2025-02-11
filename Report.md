# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project.

## Technical Questions

1. What does CSV stand for?

   CSV stands for Comma-Separated Values.


2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?

   We would like to use the interface types `List` and `IEmployee` rather than the types that implements the corresponding interface because of the following
   reasons.
    1) The use of interface reduces coupling between the code and the implementation of the interface. Because the interface specifies the methods that the implementing
       class has to have, it abstracts away the implementation details of the subclasses from rest of the code. For example, the IEmployee interface may have specified that
       all implementing classes should have a method to process the payroll, but how exactly the subclasses calculate the payroll details and process the payroll is of less
       importance to the rest of the codebase.
    2) The use of interface also provides more flexibility to the implementation. The underlying implementation can be more easily changed from one subclass type to another. For example,
       by declaring the `employees` as a `List`, we are able to easily change it from an  `ArrayList` to a `LinkedList`.
    3) Polymorphism achieved through interface implementation and method overriding allows objects of different types to be treated as a common super type. For example, the `employees` list can store different types of employees such as `HourlyEmployee` and `SalaryEmployee`, and a for loop can process both of these
       subtypes because their methods have been well-defined by their superclass or the interface at an abstract level.


3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?

   In this case, it is a has-a relationship.


4. Can you provide an example of a has-a relationship in your code (if one exists)?

   Yes. My `Employee` abstract class has an object of the `PayStub` class as one of its attributes. When the payroll is processed, the `payStub` attribute
   is updated. The `Employee` abstract class also provides a public method `getPayStub` to offer access to the `payStub` attribute from outside the class.


5. Can you provide an example of an is-a relationship in your code (if one exists)?

   Yes. The `HourlyEmployee` and the `SalaryEmployee` are both `Employee` type because they both extends the `Employee` abstract class, which implements the
   `IEmployee` interface.


6. What is the difference between an interface and an abstract class?

   The differences between an interface and an abstract class are as follows.
    1) An interface defines a contract that the classes implementing the interface must follow. The interface only specifies what methods the implementing classes
       should have but does not dictate how they are implemented. An abstract class is a base class containing common codes that the subclasses can inherit.
    2) The methods in an interface are abstract methods (methods without a body) except the default and static methods, and the methods in an abstract class can be
       abstract or concrete. The default method in an interface has a default implementation (i.e., not an abstract method). The static method, which also has a method
       body, in the interface belongs to the interface itself rather than instances of the interface.
    3) A class can only extend one abstract class but implement multiple interfaces.
    4) An interface cannot have instance fields, and can only have constants. An abstract class can have instance fields and static fields.
    5) An interface does not have a constructor. An abstract class can have constructors, which are called by the subclass's constructors.


7. What is the advantage of using an interface over an abstract class?

   The advantages of using an interface over an abstract class include the following.
    1) The use of an interface decouples different code components by only specifying what methods that the subclasses should have but not how
       they should be implemented. This enables different implementations to exist at the same time.
    2) A class may implement multiple interfaces but only one abstract class. The multiple inheritance enables the subclass to combine different
       functionalities in one class.


8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it.

   No. This line of code is not valid in Java because `int` is a primitive type, and Java does not allow a primitive type to be used as a type parameter
   for a generic class like `List`. For this line of code to work, we should use `Integer` rather than `int`.


9. Which class/method is described as the "driver" for your application?

   The main method in the PayrollGenerator class is described as the driver for the application.


10. How do you create a temporary folder for JUnit Testing?

    The `@TempDir` annotation is used to create a temporary folder for JUnit Testing.


## Deeper Thinking

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits.

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that.

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity.


To address the income inequality problem, it may be useful to modify the payroll system in a few ways.
1) We could add some more fields to the employee records such as gender and ethnicity. These additional information can help identifying and analyzing any potential income inequality issues.
2) Statistical tools may be used to analyze the payroll data to identify any significant inequality in pay between different groups of employees.
3) It is possible to add functionalities of generating quarterly and annual reports showing the above analysis.
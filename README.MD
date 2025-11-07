# Basalt
Extremely fast event system made for kotlin ([~100ns/handler of event](./benchmark.MD)) 

## Limitations
- You can only subscribe a single instance of a type at the same time
    - Assuming `Bar` and `Baz` extends `Foo`, you cannot subscribe 2 instances of 
      `Bar` or 2 instances of `Baz`, however you can subscribe an instance of `Bar`
      and an instance of `Baz` at the same time.
- Static methods aren't supported (since kotlin by default shouldn't have any)
# Basalt
Extremely fast event system made for kotlin ([~100ns/event handler on post, ~0.03ms/event handler on subscribe](./benchmark.MD)) 

## Usage
You can add this as a dependency via [Jitpack](https://jitpack.io/#gkursi/basalt)

## Limitations
- You can only subscribe a single instance of a type at the same time
    - Assuming `Foo` and `Bar` extends `Baz`, you cannot subscribe 2 instances of 
      `Foo` or 2 instances of `Bar`, however you can subscribe an instance of `Foo`
      and an instance of `Bar` at the same time.
- Static methods aren't supported (since kotlin by default shouldn't have any)
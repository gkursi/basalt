```
Benchmark                                        Mode      Cnt      Score      Error   Units
MultiSubscriberBenchmark.bench                  thrpt        5  17224.042 ±  844.436  ops/ms
SingleSubscriberBenchmark.bench                 thrpt        5  58026.388 ± 7394.313  ops/ms
MultiSubscriberBenchmark.bench                   avgt        5     ≈ 10⁻⁴              ms/op
SingleSubscriberBenchmark.bench                  avgt        5     ≈ 10⁻⁵              ms/op
MultiSubscriberBenchmark.bench                 sample  1742363     ≈ 10⁻⁴              ms/op
MultiSubscriberBenchmark.bench:bench·p0.00     sample              ≈ 10⁻⁴              ms/op
MultiSubscriberBenchmark.bench:bench·p0.50     sample              ≈ 10⁻⁴              ms/op
MultiSubscriberBenchmark.bench:bench·p0.90     sample              ≈ 10⁻⁴              ms/op
MultiSubscriberBenchmark.bench:bench·p0.95     sample              ≈ 10⁻⁴              ms/op
MultiSubscriberBenchmark.bench:bench·p0.99     sample              ≈ 10⁻⁴              ms/op
MultiSubscriberBenchmark.bench:bench·p0.999    sample              ≈ 10⁻⁴              ms/op
MultiSubscriberBenchmark.bench:bench·p0.9999   sample               0.008              ms/op
MultiSubscriberBenchmark.bench:bench·p1.00     sample               0.154              ms/op
SingleSubscriberBenchmark.bench                sample  1906980     ≈ 10⁻⁴              ms/op
SingleSubscriberBenchmark.bench:bench·p0.00    sample              ≈ 10⁻⁴              ms/op
SingleSubscriberBenchmark.bench:bench·p0.50    sample              ≈ 10⁻⁴              ms/op
SingleSubscriberBenchmark.bench:bench·p0.90    sample              ≈ 10⁻⁴              ms/op
SingleSubscriberBenchmark.bench:bench·p0.95    sample              ≈ 10⁻⁴              ms/op
SingleSubscriberBenchmark.bench:bench·p0.99    sample              ≈ 10⁻⁴              ms/op
SingleSubscriberBenchmark.bench:bench·p0.999   sample              ≈ 10⁻⁴              ms/op
SingleSubscriberBenchmark.bench:bench·p0.9999  sample               0.008              ms/op
SingleSubscriberBenchmark.bench:bench·p1.00    sample               0.084              ms/op
MultiSubscriberBenchmark.bench                     ss               1.242              ms/op
SingleSubscriberBenchmark.bench                    ss               1.040              ms/op
```
# The Difference between Collect and CollectLatest
collect: Collects all values emitted by a Flow. Each emission is processed sequentially.

collectLatest: If a new value is emitted while the previous one is still being processed,
it cancels the ongoing work and starts with the latest value.
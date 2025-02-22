If you want bundle A to export a version and bundle B to import that specific version, you have to write it out.

This should be simpler:

```
bnd(
    name = "example01.api",
    bnd_inputs = {
        "Export-Package": "boinsoft.example01;version=1.0.0",
    },
    deps = [
        ":example01_api",
    ],
)

bnd(
    name = "example01.dummy",
    bnd_inputs = {
        "Private-Package": "boinsoft.example01.dummy",
        "Import-Package": "boinsoft.example01;version=1.0.0,*",
    },
    deps = [
        ":example01_dummy_service",
    ],
)
```

Maybe higher level constructs are necessary? where
`java_library` and `bnd` are grouped into a single rule:

```
atosgi.bundle(
    name = "example01.api",
    srcs = [ ... ],
    exports = atosgi.api(
       package = "boinsoft.example01",
       version = "1.0.0",
       baseline = false,
    ),
)

atosgi.bundle(
    name = "example01.command",
    srcs = [ ... ],
    deps = [
       ":example01.api", # auto-adds Import-Package with version.
    ],
)
```


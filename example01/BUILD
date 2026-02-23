load("@atosgi//:defs.bzl", "atosgi_artifact", "bnd", "bundles")
load("@rules_java//java:java_library.bzl", "java_library")

package(
    default_visibility = ["//:__subpackages__"],
)

java_library(
    name = "example01_api",
    srcs = [
        "Handle.java",
        "MachineSpecification.java",
        "ProvisionService.java",
        "Status.java",
    ],
)

java_library(
    name = "example01_dummy_service",
    srcs = [
        "dummy/DummyProvisionService.java",
    ],
    deps = [
        atosgi_artifact("org.osgi:osgi.cmpn"),
        atosgi_artifact("org.ops4j.pax.logging:pax-logging-api"),
        "//example01:example01_api",
    ],
)

java_library(
    name = "example01_command",
    srcs = [
        "command/ProvisionCommand.java",
        "command/StatusCommand.java",
    ],
    deps = [
        atosgi_artifact("org.osgi:osgi.cmpn"),
        atosgi_artifact("org.ops4j.pax.logging:pax-logging-api"),
        "//example01:example01_api",
    ],
)

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

bnd(
    name = "example01.command",
    bnd_inputs = {
        "Private-Package": "boinsoft.example01.command",
        "Import-Package": "boinsoft.example01;version=1.0.0,*",
    },
    deps = [
        ":example01_command",
    ],
)

bundles(
    name = "example01_bundles",
    bundles = [
        ":example01.api",
        ":example01.dummy",
        ":example01.command",
    ],
)

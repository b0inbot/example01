load("@atosgi//:defs.bzl", "atosgi_artifact", "bnd", "bundles")
load("@rules_java//java:java_library.bzl", "java_library")
load("@rules_jvm_external//:defs.bzl", "artifact")
load("@rules_kotlin//kotlin:jvm.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:__subpackages__"],
)

java_library(
    name = "example02_api",
    srcs = [
        "Handle.java",
        "MachineSpecification.java",
        "ProvisionService.java",
        "Status.java",
    ],
)

java_library(
    name = "example02_dummy_service",
    srcs = [
        "dummy/DummyProvisionService.java",
    ],
    deps = [
        atosgi_artifact("org.osgi:osgi.cmpn"),
        atosgi_artifact("org.ops4j.pax.logging:pax-logging-api"),
        "//example02:example02_api",
    ],
)

kt_jvm_library(
    name = "example02_command",
    srcs = [
        "command/ProvisionCommand.kt",
        "command/StatusCommand.kt",
    ],
    deps = [
        atosgi_artifact("org.osgi:osgi.cmpn"),
        atosgi_artifact("org.ops4j.pax.logging:pax-logging-api"),
        "//example02:example02_api",
    ],
)

bnd(
    name = "example02.api",
    bnd_inputs = {
        "Export-Package": "boinsoft.example02;version=1.0.0",
    },
    deps = [
        ":example02_api",
    ],
)

bnd(
    name = "example02.dummy",
    bnd_inputs = {
        "Private-Package": "boinsoft.example02.dummy",
        "Import-Package": "boinsoft.example02;version=1.0.0,*",
    },
    deps = [
        ":example02_dummy_service",
    ],
)

bnd(
    name = "example02.command",
    bnd_inputs = {
        "Private-Package": "boinsoft.example02.command",
        "Import-Package": "boinsoft.example02;version=1.0.0,*",
    },
    deps = [
        ":example02_command",
    ],
)

bundles(
    name = "example02_bundles",
    bundles = [
        ":example02.api",
        ":example02.dummy",
        ":example02.command",
        artifact(
            "org.jetbrains.kotlin:kotlin-osgi-bundle:2.3.10",
            repository_name = "boinsoft_example01_maven",
        ),
    ],
)

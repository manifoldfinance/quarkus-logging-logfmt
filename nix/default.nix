{ system ? builtins.currentSystem
, nixpkgs ? import ./nixpkgs.nix { inherit system; }
}:
let
  sources = import ./sources.nix;

  devshell = import sources.devshell { pkgs = nixpkgs; };

  mkEnv = nixpkgs.callPackage ./mkEnv.nix { };
in
rec {
  inherit
    nixpkgs
    ;

  # Docs: https://numtide.github.io/devshell/modules_schema.html
  env = devshell.mkShell {
    env = [
      # Configure nix to use nixpgks
      { name = "NIX_PATH"; value = "nixpkgs=${toString nixpkgs.path}"; }
      # Set the env for gradle and maven
      { name = "JAVA_HOME"; eval = "$DEVSHELL_DIR/lib/openjdk"; }
    ];

    packages = [
      # Development tools
      nixpkgs.gitAndTools.git-absorb
      nixpkgs.jq
      nixpkgs.just
      nixpkgs.minify
      nixpkgs.niv
      nixpkgs.shellcheck
      nixpkgs.yq

      # Code formatting
      nixpkgs.treefmt
      nixpkgs.nixpkgs-fmt
      nixpkgs.shfmt

      # Java
      nixpkgs.jdk17
      nixpkgs.maven

      nixpkgs.coreutils
      nixpkgs.gnugrep
      nixpkgs.which

    ];

    commands = [
      { name = "gradle"; category = "dev"; help = "gradle build system"; command = ''$PRJ_ROOT/gradlew "$@"''; }
      { name = "j"; category = "dev"; help = "just a command runner"; command = ''${nixpkgs.just}/bin/just "$@"''; }
      { category = "dev"; package = nixpkgs.yarn; }
    ];
  };

  nixos-configs = (nixpkgs.lib.recurseIntoAttrs (import ../ops/nixos/configs { inherit (nixpkgs) lib nixos; }));
}

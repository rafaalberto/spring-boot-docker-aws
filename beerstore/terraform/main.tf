provider "aws" {
  version = "~> 2.7"
  shared_credentials_file = "~/.aws/credentials"
  profile = "terraform"
}

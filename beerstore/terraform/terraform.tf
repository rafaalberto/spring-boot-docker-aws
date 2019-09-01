terraform {
  backend "s3" {
    bucket = "rafaalberto17-terraform-state"
    key = "beerstore-api"
    region = "us-east-1"
    profile = "terraform"
  }
}
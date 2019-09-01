resource "aws_security_group" "allow_ssh" {
  vpc_id = "${aws_vpc.main.id}"
  name = "beerstore-allow-ssh"

  ingress {
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["${var.my_public_ip}"]
  }
}

resource "aws_security_group" "database" {
  vpc_id = "${aws_vpc.main.id}"
  name = "rafaalberto-database"

  ingress {
    from_port = 5432
    to_port = 5432
    protocol = "tcp"
    self = true
  }
}
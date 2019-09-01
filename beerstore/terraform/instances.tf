resource "aws_key_pair" "keypair" {
  key_name = "beerstore_key"
  public_key = "${file("key/beerstore_key.pub")}"
}

resource "aws_instance" "intances" {
  count = 3

  ami = "ami-0b898040803850657"
  instance_type = "t2.micro"
  subnet_id = "${element(aws_subnet.public_subnet.*.id, count.index)}"

  key_name = "${aws_key_pair.keypair.key_name}"

  security_groups = ["${aws_security_group.allow_ssh.id}"]

  tags = {
    Name = "beerstore-instances"
  }
}

output "public_ips" {
  value = "${join(", ", aws_instance.intances.*.public_ip)}"
}
resource "aws_vpc" "main" {
  cidr_block = "192.168.0.0/16"

  tags = {
    Name = "beerstore-vpc"
  }
}

resource "aws_subnet" "private_subnet" {
  count = 3

  vpc_id = "${aws_vpc.main.id}"
  cidr_block = "${cidrsubnet(aws_vpc.main.cidr_block, 8, count.index + 10)}"
  availability_zone = "${var.availability_zones[count.index]}"

  tags = {
    Name = "beerstore-private-subnet-${count.index}"
  }
}

resource "aws_subnet" "public_subnet" {
  count = 3

  vpc_id = "${aws_vpc.main.id}"
  cidr_block = "${cidrsubnet(aws_vpc.main.cidr_block, 8, count.index + 20)}"
  availability_zone = "${var.availability_zones[count.index]}"
  map_public_ip_on_launch = true

  tags = {
    Name = "beerstore-public-subnet-${count.index}"
  }
}

resource "aws_internet_gateway" "internet-gateway" {
  vpc_id = "${aws_vpc.main.id}"
}

resource "aws_route_table" "route-internet-gateway" {
  vpc_id = "${aws_vpc.main.id}"

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = "${aws_internet_gateway.internet-gateway.id}"
  }
}

resource "aws_route_table_association" "route-table-association" {
  count = 3

  route_table_id = "${aws_route_table.route-internet-gateway.id}"
  subnet_id = "${element(aws_subnet.public_subnet.*.id, count.index)}"
}

output "cidr_public" {
  value = "${aws_subnet.public_subnet.*.cidr_block}"
}
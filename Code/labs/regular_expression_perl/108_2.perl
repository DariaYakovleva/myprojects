while (<>) {
	print if /^[^\(\)]*\({n}\w+\){n}[^\(\)]*$/;
}
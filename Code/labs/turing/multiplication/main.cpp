#include <iostream>
#include <string>
#include <cstdio>
#include <vector>

using namespace std;

string s = "s";
string ac = "ac";
string rj = "rj";
string b = "_";
string top = "^";
string l = "<";
string r = ">";
vector<string> states;
vector<pair<string, string>> steps;

void print_states() {
	cout << "start: " << s << endl;
	cout << "accept: " << ac << endl;
	cout << "reject: " << rj << endl;
	cout << "blank: " << b << endl;
}

void print_step(int pos) {
	cout << steps[pos].first << " -> " << steps[pos].second << endl;	
}

void add_step(string from, string st, string to, string nst, string go) {
	steps.push_back(make_pair(from + " " + st, to + " " + nst + " " + go));
}

void print_steps() {
	for (size_t i = 0; i < steps.size(); i++) {
		print_step(i);
	}
}

const int s_s = 10;
string symb[s_s] = {"0", "1", "*", "2", "3", "4", "!", "_", "$", "x"};

void to_end(string state, string stop, string next) { //go to end while current position isn't equal to stop
	for (int i = 0; i < s_s; i++) {
		if (symb[i] != stop) add_step(state, symb[i], state, symb[i], r); 	 	
	}
	add_step(state, stop, next, stop, top); 	
}

void to_begin(string state, string stop, string next) { //go to begin while current position isn't equal to stop
	for (int i = 0; i < s_s; i++) {
		if (symb[i] != stop) add_step(state, symb[i], state, symb[i], l); 	 	
	}
	add_step(state, stop, next, stop, top); 	
}


int main() {
	freopen("multiplication.out", "w", stdout);
	print_states();
	string go = "go";


	add_step(s, "1", go, "1", top); 
	add_step(s, "0", go, "0", top); 
	string setv = "setv";
	to_end(go, "_", setv);
	string gob = "gob";
	add_step(setv, "_", gob, "!", top);
	string rep = "rep";
	to_begin(gob, "_", rep);

	add_step(rep, "_", rep, "_", r); //rep -- replace cur and go to set 0 in ans
	string go2 = "go2";
	add_step(rep, "0", go2, "2", r);
	add_step(rep, "1", go2, "3", r);
	add_step(rep, "*", go2, "+", r);
	string go22 = "go22";	
	add_step(rep, "!", go22, "!", r);
	string setz = "setz";
	to_end(go2, "_", setz);  // go2 -- go to end while not blank
	string go2l = "go2l";
	add_step(setz, "_", go2l, "0", l); //setz -- set 0

	add_step(go2l, "1", go2l, "1", l);
	add_step(go2l, "0", go2l, "0", l);
	add_step(go2l, "*", go2l, "*", l);
	add_step(go2l, "+", rep, "*", r);
	add_step(go2l, "2", rep, "0", r);
	add_step(go2l, "3", rep, "1", r);
	add_step(go2l, "!", go2l, "!", l);
	add_step(go2l, "_", go2, "_", r);
	string setv2 = "setv2";		
	to_end(go22, "_", setv2);
	string gob2 = "gob2";
	add_step(setv2, "_", gob2, "!", top);
	string cb = "cb";
	string cb2 = "cb2";
	to_begin(gob2, "_", cb2);  //cb2 -- go to *

	to_end(cb2, "*", cb);

	add_step(cb, "*", cb, "*", r); //cb -- replace second num pos
	string gobr0 = "gobr0";
	string gobr1 = "gobr1", star = "star";
	add_step(cb, "0", gobr0, "2", r);
	add_step(cb, "1", gobr1, "3", r);
	add_step(cb, "!", star, "!", r); // now we should gp to star
	                                     
	add_step(gobr0, "0", gobr0, "0", r); //gobr0 -- set 0
	add_step(gobr0, "1", gobr0, "1", r);
	add_step(gobr0, "!", gobr0, "!", r);
	string rcb = "rcb";
	add_step(gobr0, "_", rcb, "0", l);

	add_step(gobr1, "0", gobr1, "0", r); // gobr1 -- set 1
	add_step(gobr1, "1", gobr1, "1", r);
	add_step(gobr1, "!", gobr1, "!", r);
	add_step(gobr1, "_", rcb, "1", l);


	add_step(rcb, "1", rcb, "1", l);
	add_step(rcb, "0", rcb, "0", l);
	add_step(rcb, "2", cb, "0", r);
	add_step(rcb, "3", cb, "1", r);
	add_step(rcb, "!", rcb, "!", l);

	// multiply 
	string mul = "mul", mul1 = "mul1", mul0 = "mul0", mset0 = "mset0", mgol = "mgol", nextm = "nextm", clr = "clr";
	to_begin(star, "*", mul);
	add_step(mul, "*", mul, "*", l);
	add_step(mul, "0", mul0, "x", top);
	add_step(mul, "1", mul1, "x", top);
	add_step(mul, "_", clr, "_", r);


	//add 0 to end
	to_end(mul0, "_", mset0);
	add_step(mset0, "_", mgol, "0", l);
	to_begin(mgol, "x", nextm);
	add_step(nextm, "x", nextm, "x", l);
	add_step(nextm, "0", mul, "0", top);
	add_step(nextm, "1", mul, "1", top);
	add_step(nextm, "_", mul, "_", top);


	//go to SUM
	to_end(mul1, "!", "sv3");
	add_step("sv3", "!", "sv4", "!", r); 
	to_end("sv4", "!", "sv");
	add_step("sv", "!", "ge", "$", r); 


	//SUM start from everything GE!
	string ge = "ge", sum = "sum", sl0 = "sl0", sl1 = "sl1", s0 = "s0", s1 = "s1", sr = "sr", ost = "ost", ggo = "ggo", ensum = "ensum";
	to_end(ge, "_", sum);
	add_step(sum, "_", sum, "_", l);
	add_step(sum, "0", sl0, "2", l);
	add_step(sum, "1", sl1, "3", l);
	add_step(sum, "!", ensum, "!", l);

	add_step(sl0, "0", sl0, "0", l);
	add_step(sl0, "1", sl0, "1", l);
	add_step(sl0, "2", s0, "0", l);
	add_step(sl0, "3", s0, "1", l);
	add_step(sl0, "$", s0, "!", l);
	add_step(sl0, "!", sl0, "!", l);

	add_step(s0, "0", sr, "2", r);
	add_step(s0, "1", sr, "3", r);

	add_step(sr, "0", sr, "0", r);
	add_step(sr, "1", sr, "1", r);
	add_step(sr, "!", sr, "!", r);
	add_step(sr, "2", sum, "0", l);
	add_step(sr, "3", sum, "1", l);
	
	add_step(sl1, "0", sl1, "0", l);
	add_step(sl1, "1", sl1, "1", l);
	add_step(sl1, "2", s1, "0", l);
	add_step(sl1, "3", s1, "1", l);
	add_step(sl1, "$", s1, "!", l);
	add_step(sl1, "!", sl1, "!", l);

	add_step(s1, "0", sr, "3", r);

	add_step(s1, "1", ost, "2", l);


	add_step(ost, "0", ggo, "1", r);
	add_step(ost, "1", ost, "0", l);

	to_end(ggo, "!", sr); 	

	add_step(ensum, "0", ensum, "0", l);
	add_step(ensum, "1", ensum, "1", l);
	add_step(ensum, "2", ensum, "0", l);
	add_step(ensum, "3", ensum, "1", l);
	add_step(ensum, "!", mul0, "!", top);


	string dd2 = "dd2", dd = "dd", ddr = "ddr", ddr2 = "ddr2", dd3 = "dd3", dd4 = "dd4";
	to_end(clr, "_", dd3);
	add_step(dd3, "_", dd, "_", l); 
	add_step(dd, "0", dd, "_", l); 
	add_step(dd, "1", dd, "_", l); 
	add_step(dd, "!", dd2, "_", l); 
	add_step(dd, "*", dd, "_", l); 
	add_step(dd, "x", dd, "_", l); 
	add_step(dd, "_", ddr, "_", r);

	to_begin(dd2, "!", dd4);
	add_step(dd4, "!", dd, "_", l);

	add_step(ddr, "_", ddr, "_", r); 
	add_step(ddr, "0", ddr2, "_", r); 
	add_step(ddr2, "0", ddr2, "_", r); 
	add_step(ddr2, "1", ac, "1", top); 
	add_step(ddr2, "_", ac, "0", top); 

            	
	print_steps();		
}
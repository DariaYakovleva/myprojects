#include "testlib.h"
using namespace std;

const int MOD = 1000000007;
const int M = 10000;
int n;
int m;

int main() {
    registerValidation();
    
    n = inf.readInt(0, M);
    inf.readSpace();
    m = inf.readInt(0, M);
    ensuref(n + m <= M, "n + m > 10000");
    inf.readEoln();
    inf.readEof();
    return 0;
}
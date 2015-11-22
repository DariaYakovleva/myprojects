#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007;
const int N = 10010;
long long dp[2][N]; //[position][balance]
int n, m;

long long go() {
	for (int i = 0; i < 2; i++) {
		for (int j = 0; j <= n + m; j++) {
			dp[i][j] = 0;
		}
	}
	dp[0][0] = 1;
	for (int i = 1; i <= n + m; i++) {
		for (int j = 0; j <= i; j++) {
			int close = (i - j) / 10;
			int open = i - close;
			dp[i % 2][j] = 0;
			if (j > 0 && open <= n) {
				dp[i % 2][j] += dp[(i - 1) % 2][j - 1];
			}
			if (j + 9 <= n + m && close <= m) {
				dp[i % 2][j] += dp[(i - 1) % 2][j + 9];
			}
		}
	}
//	for (int i = 0; i <= n + m; i++) {
//		for (int j = 0; j <= n + m; j++) {
//			cout << dp[i][j] << " ";
//		}
//		cout << endl;
//	}
	long long res = 0;
	for (int i = 0; i <= n; i++) {
		res += dp[(n + m) % 2][i];
	}
	return res;
}

int main() {
	freopen("ticket-office.in", "r", stdin);
	freopen("ticket-office.out", "w", stdout);
	cin >> n >> m;
	cout << go() << endl;
	
}

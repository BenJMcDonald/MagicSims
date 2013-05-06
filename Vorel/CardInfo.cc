#include <string>
#include <unordered_map>
#include <vector>
using namespace std;

class CardInfo{
    private:
    map<string, vector<string>> info;
    public:
    CardInfo();
    ~CardInfo();
    bool is(string, string);
};

CardInfo::CardInfo(){
    info = new map<string, vector<string>>();
    v = new vector<string>();
    v.push_back("Basic");
    v.push_back("Land");
};




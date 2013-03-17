#include "generic.h"

int main(){
    Card* c= new Card("Mountain");

    cout<<c->hintHas("burn")<<'\n';
    cout<<c->hintValue("burn")<<'\n';
}

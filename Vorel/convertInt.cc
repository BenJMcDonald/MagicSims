string convertInt(int in)
{
    string output = "";
    while(true){
	switch(in%10)
	{
	    case 1:
		output = '1' + output;
		break;
	    case 2:
		output = '2' + output;
		break;
	    case 3:
		output = '3' + output;
		break;
	    case 4:
		output = '4' + output;
		break;
	    case 5:
		output = '5' + output;
		break;
	    case 6:
		output = '6' + output;
		break;
	    case 7:
		output = '7' + output;
		break;
	    case 8:
		output = '8' + output;
		break;
	    case 9:
		output = '9' + output;
		break;
	    case 0:
		output = '0' + output;
		break;
	}
	if(in<10){
	    return output;
	}else{
	    in = in/10;
	}
    }
    return output;
}

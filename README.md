# JWT_USERID


Step 1: GET Question ID 

GET METHOD: http://localhost:8087/dashboard/102

Step 2: Call Question

http://localhost:8087/dashboard/question/1
http://localhost:8087/dashboard/question/2


example user id = 1

[
    {
        "optionC": "O2",
        "optionB": "CO2",
        "optionA": "H2O",
        "question": "What is the chemical symbol for water?",
        "optionD": "N2"
    }
]


POST request answer

useranswer 

http://localhost:8087/dashboard/mark/1/1/B

userrmark

http://localhost:8087/dashboard/mark/102/1


currentuserquestion

http://localhost:8087/dashboard/lastquestionverified/102
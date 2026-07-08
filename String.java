from sklearn.linear_model import LogisticRegression
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.ensemble import VotingClassifier

lr = LogisticRegression(random_state=42)
lr.fit(X2_train, y2_train)
y_pred_lr = lr.predict(X2_test)
dt = DecisionTreeClassifier(random_state=42)
dt.fit(X2_train, y2_train)
y_pred_dt = dt.predict(X2_test)
rf = RandomForestClassifier(
    n_estimators=100,
    random_state=42
)
rf.fit(X2_train, y2_train)
y_pred_rf = rf.predict(X2_test)
nb = GaussianNB()
nb.fit(X2_train, y2_train)
y_pred_nb = nb.predict(X2_test)from sklearn.metrics import accuracy_score

accuracy_lr = accuracy_score(y2_test, y_pred_lr)
print("Logistic Regression Accuracy:", accuracy_lr)

accuracy_dt = accuracy_score(y2_test, y_pred_dt)
print("Decision Tree Accuracy:", accuracy_dt)

accuracy_rf = accuracy_score(y2_test, y_pred_rf)
print("Random Forest Accuracy:", accuracy_rf)

accuracy_nb = accuracy_score(y2_test, y_pred_nb)
print("Naive Bayes Accuracy:", accuracy_nb)


print("Logistic Regression Accuracy:", round(accuracy_lr * 100, 2), "%")
print("Decision Tree Accuracy:", round(accuracy_dt * 100, 2), "%")
print("Random Forest Accuracy:", round(accuracy_rf * 100, 2), "%")
print("Naive Bayes Accuracy:", round(accuracy_nb * 100, 2), "%")


    import pandas as pd

accuracy_table = pd.DataFrame({
    "Model": [
        "Logistic Regression",
        "Decision Tree",
        "Random Forest",
        "Naive Bayes"
    ],
    "Accuracy (%)": [
        round(accuracy_lr * 100, 2),
        round(accuracy_dt * 100, 2),
        round(accuracy_rf * 100, 2),
        round(accuracy_nb * 100, 2)
    ]
})

print(accuracy_table)

from sklearn.metrics import accuracy_score, precision_score, recall_score
from sklearn.metrics import f1_score, confusion_matrix, classification_report

def evaluate_model(model_name, y_test, y_pred):

    print("="*50)
    print(model_name)
    print("="*50)

    print("Accuracy :", accuracy_score(y_test, y_pred))
    print("Precision :", precision_score(y_test, y_pred, average='weighted'))
    print("Recall :", recall_score(y_test, y_pred, average='weighted'))
    print("F1 Score :", f1_score(y_test, y_pred, average='weighted'))

    print("\nConfusion Matrix")
    print(confusion_matrix(y_test, y_pred))

    print("\nClassification Report")
    print(classification_report(y_test, y_pred))


    evaluate_model("Logistic Regression", y2_test, y_pred_lr)

evaluate_model("Decision Tree", y2_test, y_pred_dt)

evaluate_model("Random Forest", y2_test, y_pred_rf)

evaluate_model("Naive Bayes", y2_test, y_pred_nb)

evaluate_model("Voting Classifier", y2_test, y_pred_vote)



    from sklearn.linear_model import LogisticRegression
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.ensemble import VotingClassifier

lr = LogisticRegression(random_state=42)
lr.fit(X2_train, y2_train)
y_pred_lr = lr.predict(X2_test)
dt = DecisionTreeClassifier(random_state=42)
dt.fit(X2_train, y2_train)
y_pred_dt = dt.predict(X2_test)
rf = RandomForestClassifier(
    n_estimators=100,
    random_state=42
)
rf.fit(X2_train, y2_train)
y_pred_rf = rf.predict(X2_test)
nb = GaussianNB()
nb.fit(X2_train, y2_train)
y_pred_nb = nb.predict(X2_test)
voting = VotingClassifier(

estimators=[

('lr', lr),

('dt', dt),

('rf', rf),

('nb', nb)

],

voting='hard'

)
voting.fit(X2_train, y2_train)
y_pred_vote = voting.predict(X2_test)
    

    

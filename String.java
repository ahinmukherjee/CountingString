import pandas as pd
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score

# Store all models and their predictions
models = {
    "Logistic Regression": y_pred_lr,
    "Decision Tree": y_pred_dt,
    "Random Forest": y_pred_rf,
    "Naïve Bayes": y_pred_nb,
    "Hybrid Voting Classifier": y_pred_vote
}

# Create an empty list
results = []

# Calculate metrics for each model
for model_name, y_pred in models.items():

    accuracy = accuracy_score(y2_test, y_pred)
    precision = precision_score(y2_test, y_pred, average="weighted")
    recall = recall_score(y2_test, y_pred, average="weighted")
    f1 = f1_score(y2_test, y_pred, average="weighted")

    results.append([
        model_name,
        round(accuracy * 100, 2),
        round(precision, 4),
        round(recall, 4),
        round(f1, 4)
    ])

# Create DataFrame
results_df = pd.DataFrame(
    results,
    columns=[
        "Model",
        "Accuracy (%)",
        "Precision",
        "Recall",
        "F1-Score"
    ]
)

print("========== MODEL COMPARISON ==========")
print(results_df.to_string(index=False))




from sklearn.preprocessing import LabelEncoder

# Create encoders
district_encoder = LabelEncoder()
gender_encoder = LabelEncoder()
agegroup_encoder = LabelEncoder()
familyhistory_encoder = LabelEncoder()
risk_encoder = LabelEncoder()

# Encode District
df["District"] = district_encoder.fit_transform(df["District"])

# Encode Gender
df["Gender"] = gender_encoder.fit_transform(df["Gender"])

# Encode AgeGroup
df["AgeGroup"] = agegroup_encoder.fit_transform(df["AgeGroup"])

# Encode FamilyHistory
df["FamilyHistory"] = familyhistory_encoder.fit_transform(df["FamilyHistory"])

# Encode RiskLevel
df["RiskLevel"] = risk_encoder.fit_transform(df["RiskLevel"])


symptom_columns = [
    "Sneezing",
    "Runny nose",
    "Nasal congestion (stuffy nose)",
    "Red eyes",
    "Itchy eyes",
    "Watery eyes",
    "Itchy throat",
    "Cough",
    "Itchy skin",
    "Skin redness",
    "Skin rash",
    "Shortness of breath or asthma in cold air"
]

for col in symptom_columns:
    symptom_encoder = LabelEncoder()
    df[col] = symptom_encoder.fit_transform(df[col])


    
    

from sklearn.preprocessing import LabelEncoder

# Check the current District values
print(df["District"].head())

# Create District encoder
district_encoder = LabelEncoder()

# Convert district names into numbers
df["District"] = district_encoder.fit_transform(df["District"])

print("\nDistrict encoded successfully.")
print(df["District"].head())

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import LabelEncoder

# Replace this with your original training dataset filename
df = pd.read_csv("your_original_dataset.csv")

# Create RiskLevel encoder
risk_encoder = LabelEncoder()
df["RiskLevel"] = risk_encoder.fit_transform(df["RiskLevel"])

# Use only the limited-input columns
limited_columns = [
    "District",
    "Gender",
    "AgeGroup",
    "FamilyHistory",
    "Sneezing",
    "Runny nose",
    "Nasal congestion (stuffy nose)",
    "Red eyes",
    "Itchy eyes",
    "Watery eyes",
    "Itchy throat",
    "Cough",
    "Itchy skin",
    "Skin redness",
    "Skin rash",
    "Shortness of breath or asthma in cold air"
]

X_limited = df[limited_columns]
y_limited = df["RiskLevel"]


    X_limited_train, X_limited_test, y_limited_train, y_limited_test = train_test_split(
    X_limited,
    y_limited,
    test_size=0.20,
    random_state=42
)

limited_risk_model = RandomForestClassifier(
    n_estimators=100,
    random_state=42
)

limited_risk_model.fit(X_limited_train, y_limited_train)

print("Limited risk model trained successfully.")




    
import pandas as pd

new_patient_data = pd.read_csv(
    "new_patient_20_data_encoded_district.csv"
)

new_patient_for_prediction = new_patient_data[limited_columns]

predicted_risk = limited_risk_model.predict(new_patient_for_prediction)

new_patient_data["Predicted_RiskLevel"] = risk_encoder.inverse_transform(
    predicted_risk
)

new_patient_data.to_csv(
    "new_patient_20_prediction_results.csv",
    index=False
)

print("========== FIRST 5 PREDICTION RESULTS ==========")
print(new_patient_data.head(5).to_string(index=False))

print("\n========== LAST 5 PREDICTION RESULTS ==========")
print(new_patient_data.tail(5).to_string(index=False))

print("\nPrediction CSV file created successfully.")

    

from sklearn.preprocessing import LabelEncoder

# Create separate encoders
district_encoder = LabelEncoder()
gender_encoder = LabelEncoder()
agegroup_encoder = LabelEncoder()
familyhistory_encoder = LabelEncoder()

# Encode main categorical columns
df["District"] = district_encoder.fit_transform(df["District"])
df["Gender"] = gender_encoder.fit_transform(df["Gender"])
df["AgeGroup"] = agegroup_encoder.fit_transform(df["AgeGroup"])
df["FamilyHistory"] = familyhistory_encoder.fit_transform(df["FamilyHistory"])

# Encode Yes / No symptom columns
symptom_columns = [
    "Sneezing",
    "Runny nose",
    "Nasal congestion (stuffy nose)",
    "Red eyes",
    "Itchy eyes",
    "Watery eyes",
    "Itchy throat",
    "Cough",
    "Itchy skin",
    "Skin redness",
    "Skin rash",
    "Shortness of breath or asthma in cold air"
]

for col in symptom_columns:
    encoder = LabelEncoder()
    df[col] = encoder.fit_transform(df[col])


    
limited_columns = [
    "District",
    "Gender",
    "AgeGroup",
    "FamilyHistory",
    "Sneezing",
    "Runny nose",
    "Nasal congestion (stuffy nose)",
    "Red eyes",
    "Itchy eyes",
    "Watery eyes",
    "Itchy throat",
    "Cough",
    "Itchy skin",
    "Skin redness",
    "Skin rash",
    "Shortness of breath or asthma in cold air"
]

X_limited = df[limited_columns]
y_limited = df["RiskLevel"]

X_limited_train, X_limited_test, y_limited_train, y_limited_test = train_test_split(
    X_limited,
    y_limited,
    test_size=0.20,
    random_state=42
)

limited_risk_model = RandomForestClassifier(
    n_estimators=100,
    random_state=42
)

limited_risk_model.fit(X_limited_train, y_limited_train)

print("Limited risk model trained successfully.")



import pandas as pd

from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score

# ==================================================
# 1. LOAD ORIGINAL TRAINING DATASET
# ==================================================

df = pd.read_csv("west_bengal_cold_allergy_1000_cleaned.csv")

print("Dataset loaded successfully.")
print(df.head())

# ==================================================
# 2. DEFINE LIMITED-INPUT COLUMNS
# ==================================================

limited_columns = [
    "District",
    "Gender",
    "AgeGroup",
    "FamilyHistory",
    "Sneezing",
    "Runny nose",
    "Nasal congestion (stuffy nose)",
    "Red eyes",
    "Itchy eyes",
    "Watery eyes",
    "Itchy throat",
    "Cough",
    "Itchy skin",
    "Skin redness",
    "Skin rash",
    "Shortness of breath or asthma in cold air"
]

symptom_columns = [
    "Sneezing",
    "Runny nose",
    "Nasal congestion (stuffy nose)",
    "Red eyes",
    "Itchy eyes",
    "Watery eyes",
    "Itchy throat",
    "Cough",
    "Itchy skin",
    "Skin redness",
    "Skin rash",
    "Shortness of breath or asthma in cold air"
]

# ==================================================
# 3. ENCODE RISKLEVEL
# ==================================================

risk_encoder = LabelEncoder()
df["RiskLevel"] = risk_encoder.fit_transform(df["RiskLevel"])

print("\nRiskLevel categories:")
print(risk_encoder.classes_)

# ==================================================
# 4. ENCODE DISTRICT AND OTHER TEXT COLUMNS
# ==================================================

district_encoder = LabelEncoder()
df["District"] = district_encoder.fit_transform(df["District"])

columns_to_encode = [
    "Gender",
    "AgeGroup",
    "FamilyHistory"
] + symptom_columns

for col in columns_to_encode:
    encoder = LabelEncoder()
    df[col] = encoder.fit_transform(df[col])

print("\nAll limited-input columns encoded successfully.")

# ==================================================
# 5. CREATE X AND y
# ==================================================

X_limited = df[limited_columns]
y_limited = df["RiskLevel"]

# ==================================================
# 6. TRAIN-TEST SPLIT
# ==================================================

X_limited_train, X_limited_test, y_limited_train, y_limited_test = train_test_split(
    X_limited,
    y_limited,
    test_size=0.20,
    random_state=42,
    stratify=y_limited
)

# ==================================================
# 7. TRAIN LIMITED RISK MODEL
# ==================================================

limited_risk_model = RandomForestClassifier(
    n_estimators=100,
    random_state=42
)

limited_risk_model.fit(X_limited_train, y_limited_train)

print("\nLimited risk model trained successfully.")

# ==================================================
# 8. CHECK MODEL ACCURACY
# ==================================================

y_pred = limited_risk_model.predict(X_limited_test)

accuracy = accuracy_score(y_limited_test, y_pred)

print("Limited Model Accuracy:", round(accuracy * 100, 2), "%")




import pandas as pd

# Load the 20-patient CSV file
new_patient_data = pd.read_csv("new_patient_20_data.csv")

# Encode only District column
new_patient_data["District"] = district_encoder.transform(
    new_patient_data["District"]
)

# Save the updated CSV file
new_patient_data.to_csv(
    "new_patient_20_data_encoded_district.csv",
    index=False
)

print(new_patient_data)
print("District column encoded successfully.")

import pandas as pd

# Load the CSV where only District is already encoded
new_patient_data = pd.read_csv(
    "new_patient_20_data_encoded_district.csv"
)

# Ensure the CSV columns match the model training columns and order
new_patient_for_prediction = new_patient_data[
    X_limited_train.columns
]

# Predict RiskLevel for all 20 patients
predicted_risk = limited_risk_model.predict(
    new_patient_for_prediction
)

# Convert encoded predictions back to Low / Medium / High
predicted_risk_text = risk_encoder.inverse_transform(
    predicted_risk
)

# Add prediction as a new column
new_patient_data["Predicted_RiskLevel"] = predicted_risk_text

# Display all patient fields with their predicted risk
print("========== PREDICTION RESULTS ==========")
print(new_patient_data.to_string(index=False))

# Save the final result in a new CSV file
new_patient_data.to_csv(
    "new_patient_20_prediction_results.csv",
    index=False
)

print("\nPrediction CSV file created successfully.")


# Add prediction as a new column
new_patient_data["Predicted_RiskLevel"] = predicted_risk_text

# Save ALL prediction results in a new CSV file
new_patient_data.to_csv(
    "new_patient_20_prediction_results.csv",
    index=False
)

# Show only first 5 rows
print("========== FIRST 5 PREDICTION RESULTS ==========")
print(new_patient_data.head(5).to_string(index=False))

# Show only last 5 rows
print("\n========== LAST 5 PREDICTION RESULTS ==========")
print(new_patient_data.tail(5).to_string(index=False))

print("\nAll prediction results are saved in: new_patient_20_prediction_results.csv")

import pandas as pd

# Load the CSV where only District is encoded
new_patient_data = pd.read_csv(
    "new_patient_20_data_encoded_district.csv"
)

# Keep the same column order used during model training
new_patient_for_prediction = new_patient_data[
    X_limited_train.columns
]

# Predict RiskLevel for all patients
predicted_risk = limited_risk_model.predict(
    new_patient_for_prediction
)

# Convert encoded prediction numbers to Low / Medium / High
predicted_risk_text = risk_encoder.inverse_transform(
    predicted_risk
)

# Add predicted RiskLevel to the original patient data
new_patient_data["Predicted_RiskLevel"] = predicted_risk_text

# Save ALL prediction results in a new CSV file
new_patient_data.to_csv(
    "new_patient_20_prediction_results.csv",
    index=False
)

# Show only the first 5 rows
print("========== FIRST 5 PREDICTION RESULTS ==========")
print(new_patient_data.head(5).to_string(index=False))

# Show only the last 5 rows
print("\n========== LAST 5 PREDICTION RESULTS ==========")
print(new_patient_data.tail(5).to_string(index=False))

print("\nAll prediction results are saved in: new_patient_20_prediction_results.csv")


import pandas as pd

# Load the 20-medicine CSV file
medicine_data = pd.read_csv(
    "cold_allergy_20_medicine_names(1).csv"
)

# Store all medicine names as a list
medicine_list = medicine_data["MedicineName"].tolist()


def get_research_medicines(row):
    selected_medicines = []

    # Breathing difficulty: do not automatically select medicine
    if row["Shortness of breath or asthma in cold air"] == 1:
        return "Clinical Review Required"

    # Skin-related symptoms
    if (
        row["Itchy skin"] == 1
        or row["Skin redness"] == 1
        or row["Skin rash"] == 1
    ):
        selected_medicines.append("Calamine lotion")

    # Eye-related symptoms
    if (
        row["Red eyes"] == 1
        or row["Itchy eyes"] == 1
        or row["Watery eyes"] == 1
    ):
        selected_medicines.append("Ketotifen eye drops")

    # Nasal congestion
    if row["Nasal congestion (stuffy nose)"] == 1:
        selected_medicines.append("Saline nasal spray")

    # Sneezing or runny nose
    if row["Sneezing"] == 1 or row["Runny nose"] == 1:
        selected_medicines.append("Cetirizine")

    # Keep only one or two medicine names
    selected_medicines = selected_medicines[:2]

    # If no symptoms are selected
    if len(selected_medicines) == 0:
        return "No medicine label generated"

    return " | ".join(selected_medicines)


# Add one output column containing one or two research medicine labels
new_patient_data["Predicted_Medicines"] = new_patient_data.apply(
    get_research_medicines,
    axis=1
)

# Save final result CSV
new_patient_data.to_csv(
    "final_cold_allergy_risk_and_medicine_results.csv",
    index=False
)

print("========== FIRST 5 FINAL RESULTS ==========")
print(new_patient_data.head(5).to_string(index=False))

print("\n========== LAST 5 FINAL RESULTS ==========")
print(new_patient_data.tail(5).to_string(index=False))

print(
    "\nFinal CSV created: "
    "final_cold_allergy_risk_and_medicine_results.csv"
)


# Print every patient detail with prediction and medicine name
for index, row in new_patient_data.iterrows():

    print("\n========================================")
    print(f"PATIENT NUMBER: {index + 1}")
    print("========================================")

    print("\n========== PATIENT DETAILS ==========")

    print("District:", row["District"])
    print("Gender:", row["Gender"])
    print("AgeGroup:", row["AgeGroup"])
    print("FamilyHistory:", row["FamilyHistory"])
    print("Sneezing:", row["Sneezing"])
    print("Runny nose:", row["Runny nose"])
    print("Nasal congestion (stuffy nose):", row["Nasal congestion (stuffy nose)"])
    print("Red eyes:", row["Red eyes"])
    print("Itchy eyes:", row["Itchy eyes"])
    print("Watery eyes:", row["Watery eyes"])
    print("Itchy throat:", row["Itchy throat"])
    print("Cough:", row["Cough"])
    print("Itchy skin:", row["Itchy skin"])
    print("Skin redness:", row["Skin redness"])
    print("Skin rash:", row["Skin rash"])
    print(
        "Shortness of breath or asthma in cold air:",
        row["Shortness of breath or asthma in cold air"]
    )

    print("\n========== PREDICTION ==========")
    print("Predicted Risk Level:", row["Predicted_RiskLevel"])
    print("Predicted Medicine:", row["Predicted_Medicines"])

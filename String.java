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

X_limited = df[
[
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
]

y_limited = df["RiskLevel"]

    from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier

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


    new_patient_limited = pd.DataFrame([{
    "District": 12,
    "Gender": 1,
    "AgeGroup": 0,
    "FamilyHistory": 1,
    "Sneezing": 1,
    "Runny nose": 1,
    "Nasal congestion (stuffy nose)": 1,
    "Red eyes": 0,
    "Itchy eyes": 1,
    "Watery eyes": 1,
    "Itchy throat": 1,
    "Cough": 1,
    "Itchy skin": 0,
    "Skin redness": 0,
    "Skin rash": 0,
    "Shortness of breath or asthma in cold air": 1
}])
predicted_risk = limited_risk_model.predict(new_patient_limited)

print(
    "Predicted Risk Level:",
    risk_encoder.inverse_transform(predicted_risk)[0]
)


    
# Patient data: use None for values the patient did not provide
new_patient = pd.DataFrame([{
    "District": 12,
    "MinTempC": None,
    "MaxTempC": None,
    "Gender": 1,
    "AgeGroup": 0,
    "FamilyHistory": 1,
    "HumidityPercent": None,
    "Altitude (m)": None,
    "WindSpeed kmph": None,
    "PM2.5": None,
    "IndoorExposure": None,
    "Sneezing": 1,
    "Runny nose": 1,
    "Nasal congestion (stuffy nose)": 1,
    "Red eyes": 0,
    "Itchy eyes": 1,
    "Watery eyes": 1,
    "Itchy throat": 1,
    "Cough": 1,
    "Itchy skin": 0,
    "Skin redness": 0,
    "Skin rash": 0,
    "Shortness of breath or asthma in cold air": 1,
    "SymptomScore": None
}])

# Keep the original patient data for display
patient_display = new_patient.copy()

# Fill missing numeric values only for model prediction
numeric_columns = [
    "MinTempC",
    "MaxTempC",
    "HumidityPercent",
    "Altitude (m)",
    "WindSpeed kmph",
    "PM2.5",
    "IndoorExposure",
    "SymptomScore"
]

for col in numeric_columns:
    if new_patient[col].isnull().any():
        new_patient[col] = new_patient[col].fillna(df[col].median())

# Ensure column order matches the trained Random Forest model
new_patient_for_prediction = new_patient[X2_train.columns]

# Predict risk level
predicted_risk = rf.predict(new_patient_for_prediction)
risk_level = risk_encoder.inverse_transform(predicted_risk)[0]

# Print every patient field
print("========== PATIENT DETAILS ==========")

for column in patient_display.columns:
    value = patient_display.loc[0, column]

    if pd.isna(value):
        print(f"{column}: Not provided")
    else:
        print(f"{column}: {value}")

print("\n========== PREDICTION ==========")
print("Predicted Risk Level:", risk_level)

    
    

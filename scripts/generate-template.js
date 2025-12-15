const fs = require("fs");

// Load JSON config
const config = JSON.parse(fs.readFileSync("config/onboarding-options.json", "utf-8"));

const yaml = `name: IBE Integration Onboarding Request
description: Submit required details for onboarding a new hospital container
title: "[Onboarding] "
labels: ["pending-input"]

body:

  - type: input
    id: hospital_name
    attributes:
      label: Hospital Name
      description: Enter hospital name (use lowercase + hyphens)
      placeholder: "aster-hebbal"
    validations:
      required: true

  - type: input
    id: sales_order
    attributes:
      label: Sales Order Number (SO)
      placeholder: "SO-0001"
    validations:
      required: true

  - type: dropdown
    id: cpu
    attributes:
      label: ${config.cpu.label}
      description: Select number of CPU cores required
      multiple: false
      options:
${config.cpu.options.map(o => `        - "${o}"`).join("\n")}
    validations:
      required: true

  - type: dropdown
    id: memory
    attributes:
      label: ${config.memory.label}
      description: Choose required memory
      options:
${config.memory.options.map(o => `        - "${o}"`).join("\n")}
    validations:
      required: true

  - type: dropdown
    id: storage
    attributes:
      label: ${config.storage.label}
      description: Select required storage size
      options:
${config.storage.options.map(o => `        - "${o}"`).join("\n")}
    validations:
      required: true

  - type: dropdown
    id: business_unit
    attributes:
      label: ${config.business_unit.label}
      description: Select the Business Unit
      options:
${config.business_unit.options.map(o => `        - "${o}"`).join("\n")}
    validations:
      required: true

  - type: input
    id: namespace
    attributes:
      label: Kubernetes Namespace
      placeholder: "aster-hebbal-ns"
    validations:
      required: true

  - type: dropdown
    id: environment
    attributes:
      label: ${config.environment.label}
      options:
${config.environment.options.map(o => `        - "${o}"`).join("\n")}
    validations:
      required: true

  - type: input
    id: ports
    attributes:
      label: Inbound Ports (Optional)
      placeholder: "8080"
    validations:
      required: false

  - type: textarea
    id: notes
    attributes:
      label: Additional Notes
      description: Add any special requirements
      placeholder: "Optional notes here..."
    validations:
      required: false
`;

fs.writeFileSync(".github/ISSUE_TEMPLATE/onboardingIssueTemplate.yml", yaml);
console.log("✔ Issue Template updated from JSON.");

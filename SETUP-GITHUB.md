# GitHub Setup Guide for Team A Service

This guide will help you push the team-a-service to your GitHub account `Duggireddy-NSF` and set up the complete GitOps pipeline.

## Prerequisites

- Git installed on your machine
- GitHub account: `Duggireddy-NSF`
- GitHub CLI (optional but recommended): `gh auth login`

## Step 1: Create Repository on GitHub

### Option A: Using GitHub Web Interface
1. Go to https://github.com/Duggireddy-NSF
2. Click "New" to create a new repository
3. Repository name: `team-a-service`
4. Description: `Team A Service for GitOps POC with FluxCD and GitHub Packages`
5. Set visibility to **Public** or **Private** (your choice)
6. **Do NOT initialize** with README, .gitignore, or license (we already have these files)
7. Click "Create repository"

### Option B: Using GitHub CLI (if installed)
```bash
gh repo create Duggireddy-NSF/team-a-service \
  --description "Team A Service for GitOps POC with FluxCD and GitHub Packages" \
  --public
```

## Step 2: Initialize Git and Push to GitHub

Navigate to your team-a-service directory and run these commands:

```bash
# Navigate to the team-a-service directory
cd team-a-service

# Initialize git repository
git init

# Add all files
git add .

# Create initial commit
git commit -m "🚀 Initial commit: Team A Service GitOps POC

- Spring Boot 3.2.0 with Java 17
- Complete REST API with health checks
- Multi-stage Docker build with security
- Helm chart for Kubernetes deployment
- GitHub Actions CI/CD pipeline
- GitHub Packages integration
- FluxCD GitOps ready"

# Add your GitHub repository as remote origin
git remote add origin https://github.com/Duggireddy-NSF/team-a-service.git

# Push to GitHub
git branch -M main
git push -u origin main
```

## Step 3: Verify Repository Setup

After pushing, verify your repository at:
https://github.com/Duggireddy-NSF/team-a-service

You should see all files including:
- Source code in `src/`
- Dockerfile
- Helm chart in `helm/`
- GitHub Actions workflow in `.github/workflows/`
- README.md and other documentation

## Step 4: Enable GitHub Packages

### 4.1 Repository Settings
1. Go to your repository: https://github.com/Duggireddy-NSF/team-a-service
2. Click on "Settings" tab
3. Scroll down to "Features" section
4. Ensure "Packages" is enabled

### 4.2 Package Visibility
1. In repository Settings → General → Features
2. Configure package visibility (inherit from repository or set independently)

## Step 5: Configure GitHub Actions Permissions

### 5.1 Workflow Permissions
1. Go to repository Settings → Actions → General
2. Under "Workflow permissions", select:
   - **Read and write permissions**
   - ✅ Check "Allow GitHub Actions to create and approve pull requests"

### 5.2 Package Permissions
1. Go to repository Settings → Actions → General
2. Under "Workflow permissions", ensure workflows can:
   - Write to packages
   - Write to repository contents

## Step 6: Test the CI/CD Pipeline

### 6.1 Trigger First Build
The GitHub Actions workflow will automatically trigger when you push to the main branch. To monitor:

1. Go to repository → "Actions" tab
2. You should see "Build and Deploy Team A Service" workflow running
3. Click on the workflow run to see details

### 6.2 Expected Pipeline Flow
1. **Test Job**: Runs Maven tests
2. **Build-and-Deploy Job**: 
   - Builds Spring Boot application
   - Creates Docker image
   - Pushes to GitHub Container Registry (ghcr.io)
   - Updates Helm values with new image tag
   - Commits changes back to repository
3. **Security-Scan Job**: Scans container for vulnerabilities

## Step 7: Verify GitHub Container Registry

After successful pipeline run:

1. Go to your repository main page
2. On the right side, look for "Packages" section
3. You should see `team-a-service` package
4. Click on it to see package details and tags

The image will be available at:
```
ghcr.io/duggireddy-nsf/team-a-service:latest
ghcr.io/duggireddy-nsf/team-a-service:main-<commit-sha>
```

## Step 8: Local Testing (Optional)

Test the Docker image locally:

```bash
# Pull the image from GitHub Container Registry
docker pull ghcr.io/duggireddy-nsf/team-a-service:latest

# Run the container
docker run -p 8080:8080 ghcr.io/duggireddy-nsf/team-a-service:latest

# Test the API endpoints
curl http://localhost:8080/
curl http://localhost:8080/hello
curl http://localhost:8080/api/info
curl http://localhost:8080/actuator/health
```

## Step 9: Troubleshooting

### Common Issues

**🔴 GitHub Actions Permission Denied**
- Ensure workflow permissions are set to "Read and write"
- Check that "Allow GitHub Actions to create and approve pull requests" is enabled

**🔴 Package Push Failed**
- Verify repository visibility settings
- Ensure GitHub Packages is enabled for your account/organization
- Check that GITHUB_TOKEN has package write permissions

**🔴 Build Fails**
- Check Java/Maven setup in the workflow
- Verify Dockerfile syntax
- Check application.yml configuration

**🔴 Image Pull Failed**
- Verify package visibility (public vs private)
- Check authentication for private packages
- Ensure image name matches repository structure

### Getting Help

1. **GitHub Actions Logs**: Check workflow run details for error messages
2. **Package Registry**: Verify packages are published correctly
3. **Repository Issues**: Create issues in your repository for tracking

## Step 10: Next Steps

After successful setup:

1. **FluxCD Integration**: Set up FluxCD to monitor this repository
2. **Kubernetes Deployment**: Deploy using the Helm chart
3. **Team Onboarding**: Use this as a template for other team repositories
4. **Monitoring**: Set up Prometheus monitoring for the service

## Repository Structure Verification

Your repository should now have this structure:

```
team-a-service/
├── .github/
│   └── workflows/
│       └── build-deploy.yml
├── helm/
│   ├── Chart.yaml
│   ├── values.yaml
│   └── templates/
│       ├── _helpers.tpl
│       ├── configmap.yaml
│       ├── deployment.yaml
│       ├── service.yaml
│       └── serviceaccount.yaml
├── src/
│   ├── main/
│   │   ├── java/com/example/teama/
│   │   └── resources/application.yml
│   └── test/
│       └── java/com/example/teama/
├── Dockerfile
├── pom.xml
├── README.md
├── .gitignore
└── SETUP-GITHUB.md
```

## Congratulations! 🎉

Your team-a-service is now configured with:
- ✅ GitHub repository with complete GitOps setup
- ✅ Automated CI/CD pipeline
- ✅ GitHub Container Registry integration
- ✅ Security scanning
- ✅ Helm chart for Kubernetes deployment
- ✅ FluxCD ready configuration

The service is ready for GitOps deployments with FluxCD!

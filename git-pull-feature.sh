echo "dailyfeed-code"
cd dailyfeed-code
git pull origin $1
cd ..
echo ""


echo "dailyfeed-redis-support"
cd dailyfeed-redis-support
git pull origin $1
cd ..
echo ""


echo "dailyfeed-member"
cd dailyfeed-member
git pull origin $1
cd ..
echo ""

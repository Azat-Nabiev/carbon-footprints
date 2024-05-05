export const getDateFromArray = (strArr) => {
  if (Array.isArray(strArr) && strArr.length >= 3) {
    const year = parseInt(strArr[0], 10);
    const month = parseInt(strArr[1], 10) - 1;
    const day = parseInt(strArr[2], 10);
    const date = new Date(year, month, day);
    return date.toLocaleDateString();
  }
  return "";
};

export const isHouse = (type) => {
  if (typeof type === "string") {
    return type.toLowerCase().includes("house");
  }
  return false;
};

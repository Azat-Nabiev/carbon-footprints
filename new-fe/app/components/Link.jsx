import NextLink from "next/link";

const Link = ({ children, active, className, ...props }) => {
  return (
    <NextLink
      className={`link${active ? "--active" : ""}${
        className ? " " + className : ""
      }`}
      {...props}
    >
      {children}
    </NextLink>
  );
};

export default Link;
